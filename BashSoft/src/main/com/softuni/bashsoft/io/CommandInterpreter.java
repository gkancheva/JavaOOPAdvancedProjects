package main.com.softuni.bashsoft.io;

import main.com.softuni.bashsoft.annotations.Alias;
import main.com.softuni.bashsoft.annotations.Inject;
import main.com.softuni.bashsoft.contracts.Executable;
import main.com.softuni.bashsoft.judge.Tester;
import main.com.softuni.bashsoft.repository.StudentRepository;
import main.com.softuni.bashsoft.network.DownloadManager;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

public class CommandInterpreter {
    private static final String COMMANDS_LOCATION = "src/com/softuni/bashsoft/io/commands";
    private static final String COMMANDS_PACKAGE = "com.softuni.bashsoft.io.commands.";

    private Tester tester;
    private StudentRepository repository;
    private DownloadManager downloadManager;
    private IOManager ioManager;

    public CommandInterpreter(Tester tester,
                              StudentRepository repository,
                              DownloadManager downloadManager,
                              IOManager ioManager) {
        this.tester = tester;
        this.repository = repository;
        this.downloadManager = downloadManager;
        this.ioManager = ioManager;
    }

    public void interpretCommand(String input) throws IOException {
        String[] data = input.split("\\s+");
        String commandName = data[0].toLowerCase();
        try {
            Executable command = parseCommand(input, data, commandName);
            command.execute();
        } catch (Exception e) {
            OutputWriter.displayException(e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private Executable parseCommand(String input, String[] data, String command) {
        File commandsFolder = new File(COMMANDS_LOCATION);
        Executable executable = null;
        for (File file : commandsFolder.listFiles()) {
            if(!file.isFile() || !file.getName().endsWith(".java")) {
                continue;
            }
            try {
                String className = file.getName()
                        .substring(0, file.getName().lastIndexOf("."));
                Class<Executable> executableClass =
                        (Class<Executable>) Class.forName(COMMANDS_PACKAGE + className);
                if(!executableClass.isAnnotationPresent(Alias.class)) {
                    continue;
                }
                Alias alias = executableClass.getAnnotation(Alias.class);
                String value = alias.value();
                if(value.equalsIgnoreCase(command)) {
                    Constructor exeConstructor = executableClass.getConstructor(String.class, String[].class);
                    executable = (Executable)exeConstructor.newInstance(input, data);
                    this.injectDependencies(executable, executableClass);
                    break;
                }
            } catch (ReflectiveOperationException e) {
                e.printStackTrace();
            }
        }
        return executable;
    }

    private void injectDependencies(Executable executable, Class<Executable> executableClass) throws IllegalAccessException {
        Field[] exeFields = executableClass.getDeclaredFields();
        for (Field exeField : exeFields) {
            if(!exeField.isAnnotationPresent(Inject.class)) {
                continue;
            }
            exeField.setAccessible(true);
            Field[] fields = CommandInterpreter.class.getDeclaredFields();
            for (Field field : fields) {
                if(!field.getType().equals(exeField.getType())) {
                    continue;
                }
                field.setAccessible(true);
                exeField.set(executable, field.get(this));
            }
        }
    }

}