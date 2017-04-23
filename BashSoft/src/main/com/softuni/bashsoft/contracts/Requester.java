package main.com.softuni.bashsoft.contracts;

import main.com.softuni.bashsoft.data_structures.SimpleSortedList;
import main.com.softuni.bashsoft.models.Course;
import main.com.softuni.bashsoft.models.Student;

import java.util.Comparator;

public interface Requester {
    void getStudentMarkInCourse(String courseName, String studentSname);
    void getStudentsByCourse(String courseName);
    SimpleSortedList<Course> getAllCoursesSorted(Comparator<Course> comparator);
    SimpleSortedList<Student> getAllStudentsSorted(Comparator<Student> comparator);
}