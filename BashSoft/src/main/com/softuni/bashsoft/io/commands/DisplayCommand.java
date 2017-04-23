package main.com.softuni.bashsoft.io.commands;

import main.com.softuni.bashsoft.annotations.Alias;
import main.com.softuni.bashsoft.annotations.Inject;
import main.com.softuni.bashsoft.data_structures.SimpleSortedList;
import main.com.softuni.bashsoft.exceptions.InvalidInputException;
import main.com.softuni.bashsoft.io.OutputWriter;
import main.com.softuni.bashsoft.models.Course;
import main.com.softuni.bashsoft.models.Student;
import main.com.softuni.bashsoft.repository.StudentRepository;

import java.util.Comparator;

@Alias("display")
public class DisplayCommand extends Command {
    private static final String COURSES = "courses";
    private static final String STUDENTS = "students";

    @Inject
    private StudentRepository studentRepository;

    public DisplayCommand(String input, String[] data) {
        super(input, data);
    }

    @Override
    public void execute() {
        String[] data = this.getData();
        if (data.length != 3) {
            throw new InvalidInputException(this.getInput());
        }
        String entityToDisplay = data[1];
        String sortType = data[2];
        if(entityToDisplay.equalsIgnoreCase(STUDENTS)) {
            Comparator<Student> studentComparator =
                    this.createStudentComparator(sortType);
            SimpleSortedList<Student> list =
                    this.studentRepository.getAllStudentsSorted(studentComparator);
            OutputWriter.writeMessageOnNewLine(list.joinWith(System.lineSeparator()));
        } else if(entityToDisplay.equalsIgnoreCase(COURSES)) {
            Comparator<Course> studentComparator =
                    this.createCourseComparator(sortType);
            SimpleSortedList<Course> list =
                    this.studentRepository.getAllCoursesSorted(studentComparator);
            OutputWriter.writeMessageOnNewLine(list.joinWith(System.lineSeparator()));
        } else {
            throw new InvalidInputException(this.getInput());
        }
    }

    private Comparator<Course> createCourseComparator(String sortType) {
        if(sortType.equalsIgnoreCase("ascending")) {
            return (o1, o2) -> o1.compareTo(o2);
        } else if(sortType.equalsIgnoreCase("descending")) {
            return (o1, o2) -> o2.compareTo(o1);
        } else {
            throw new InvalidInputException(this.getInput());
        }
    }

    private Comparator<Student> createStudentComparator(String sortType) {
        if(sortType.equalsIgnoreCase("ascending")) {
            return (o1, o2) -> o1.compareTo(o2);
        } else if(sortType.equalsIgnoreCase("descending")) {
            return (o1, o2) -> o2.compareTo(o1);
        } else {
            throw new InvalidInputException(this.getInput());
        }
    }
}
