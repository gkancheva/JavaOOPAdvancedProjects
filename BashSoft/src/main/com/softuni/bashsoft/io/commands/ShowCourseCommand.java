package main.com.softuni.bashsoft.io.commands;

import main.com.softuni.bashsoft.annotations.Alias;
import main.com.softuni.bashsoft.annotations.Inject;
import main.com.softuni.bashsoft.exceptions.InvalidInputException;
import main.com.softuni.bashsoft.repository.StudentRepository;

@Alias("show")
public class ShowCourseCommand extends Command {

    @Inject
    private StudentRepository studentRepository;

    public ShowCourseCommand(String input, String[] data) {
        super(input, data);
    }

    @Override
    public void execute() {
        String[] data = this.getData();
        if (data.length != 2 && data.length != 3) {
            throw new InvalidInputException(this.getInput());
        }
        if (data.length == 2) {
            String courseName = data[1];
            this.studentRepository.getStudentsByCourse(courseName);
            return;
        }
        String courseName = data[1];
        String userName = data[2];
        this.studentRepository.getStudentsMarkForCourse(courseName, userName);

    }
}
