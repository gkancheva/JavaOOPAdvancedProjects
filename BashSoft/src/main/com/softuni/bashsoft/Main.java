package main.com.softuni.bashsoft;

import main.com.softuni.bashsoft.io.CommandInterpreter;
import main.com.softuni.bashsoft.io.IOManager;
import main.com.softuni.bashsoft.io.InputReader;
import main.com.softuni.bashsoft.io.OutputWriter;
import main.com.softuni.bashsoft.judge.Tester;
import main.com.softuni.bashsoft.repository.RepositoryFilter;
import main.com.softuni.bashsoft.repository.RepositorySorter;
import main.com.softuni.bashsoft.repository.StudentRepository;
import main.com.softuni.bashsoft.network.DownloadManager;

public class Main {
    public static void main(String[] args) {
        Tester tester = new Tester();
        DownloadManager downloadManager = new DownloadManager();
        IOManager ioManager = new IOManager();
        RepositorySorter sorter = new RepositorySorter();
        RepositoryFilter filter = new RepositoryFilter();
        StudentRepository repository = new StudentRepository(filter, sorter);
        CommandInterpreter interpreter = new CommandInterpreter(tester, repository, downloadManager, ioManager);
        InputReader reader = new InputReader(interpreter);
        try {
            reader.readCommands();
        } catch (Exception e) {
            OutputWriter.displayException(e.getMessage());
        }
    }
}
