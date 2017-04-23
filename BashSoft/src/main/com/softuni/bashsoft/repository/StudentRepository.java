package main.com.softuni.bashsoft.repository;

import main.com.softuni.bashsoft.contracts.Requester;
import main.com.softuni.bashsoft.data_structures.SimpleSortedList;
import main.com.softuni.bashsoft.static_data.ExceptionMessages;
import main.com.softuni.bashsoft.io.OutputWriter;
import main.com.softuni.bashsoft.static_data.Messages;
import main.com.softuni.bashsoft.static_data.SessionData;
import main.com.softuni.bashsoft.models.Course;
import main.com.softuni.bashsoft.models.Student;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StudentRepository implements Requester {
    private boolean isDataInitialized = false;
    private Map<String, Course> courses;
    private Map<String, Student> students;

    private RepositoryFilter filter;
    private RepositorySorter sorter;

    public StudentRepository(RepositoryFilter filter, RepositorySorter sorter) {
        this.filter = filter;
        this.sorter = sorter;
    }

    public void loadData(String fileName) throws IOException {
        if(this.isDataInitialized) {
            throw new RuntimeException(ExceptionMessages.DATA_ALREADY_INITIALIZED);
        }
        this.students = new LinkedHashMap<>();
        this.courses = new LinkedHashMap<>();
        this.readData(fileName);
    }

    public void unloadData() {
        if(!this.isDataInitialized) {
            throw new RuntimeException(ExceptionMessages.DATA_NOT_INITIALIZED);
        }
        this.students = null;
        this.courses = null;
        this.isDataInitialized = false;
    }

    private void readData(String fileName) throws IOException {
        String regex = "([A-Z][a-zA-Z#\\+]*_[A-Z][a-z]{2}_\\d{4})\\s+([A-Za-z]+\\d{2}_\\d{2,4})\\s([\\s0-9]+)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher;
        String path = SessionData.currentPath + "\\" + fileName;
        List<String> lines = Files.readAllLines(Paths.get(path));
        for (int lineIndex = 0; lineIndex < lines.size(); lineIndex++) {
            String line = lines.get(lineIndex);
            matcher = pattern.matcher(line);
            if (!line.isEmpty() && matcher.find()) {
                String courseName = matcher.group(1);
                String studentName = matcher.group(2);
                String scoresStr = matcher.group(3);
                try {
                    String[] splitScores = scoresStr.split("\\s+");
                    int[] scores = new int[splitScores.length];
                    for (int i = 0; i < splitScores.length; i++) {
                        scores[i] = Integer.parseInt(splitScores[i]);
                    }
                    if (Arrays.stream(scores)
                            .anyMatch(score -> score > 100 || score < 0)) {
                        OutputWriter.displayException(
                                ExceptionMessages.INVALID_SCORE);
                        continue;
                    }
                    if (scores.length > Course.NUMBER_OF_TASKS_ON_EXAM) {
                        OutputWriter.displayException(
                                ExceptionMessages.INVALID_NUMBER_OF_SCORES);
                        continue;
                    }
                    if (!this.students.containsKey(studentName)) {
                        this.students.put(studentName, new Student(studentName));
                    }
                    if (!this.courses.containsKey(courseName)) {
                        this.courses.put(courseName, new Course(courseName));
                    }
                    Course course = this.courses.get(courseName);
                    Student student = this.students.get(studentName);
                    student.enrollInCourse(course);
                    student.setMarkOnCourse(courseName, scores);
                    course.enrollStudent(student);
                } catch (NumberFormatException nfe) {
                    OutputWriter.displayException(nfe.getMessage() + " at line: " + lineIndex);
                }
            }
        }
        isDataInitialized = true;
        OutputWriter.writeMessageOnNewLine(Messages.DATA_READ);
    }

    public void getStudentsMarkForCourse(String courseName, String studentName) {
        if(!isQueryForStudentPossible(courseName, studentName)) {
            return;
        }
        String result = this.students.get(studentName).getMarkForCourse(courseName);
        OutputWriter.writeMessageOnNewLine(result);
    }

    @Override
    public void getStudentMarkInCourse(String courseName, String studentSname) {
        if(!isQueryForStudentPossible(courseName, studentSname)) {
            return;
        }
        String result = this.students.get(studentSname).getMarkForCourse(courseName);
        OutputWriter.writeMessageOnNewLine(result);
    }

    public void getStudentsByCourse(String courseName) {
        if(!isQueryForCoursePossible(courseName)) {
            return;
        }
        OutputWriter.writeMessageOnNewLine(courseName + ":");
        for(Map.Entry<String, Student> student: this.courses.get(courseName).getStudentsByName().entrySet()) {
            this.getStudentsMarkForCourse(courseName, student.getKey());
        }
    }

    @Override
    public SimpleSortedList<Course> getAllCoursesSorted(Comparator<Course> comparator) {
        if(!isDataInitialized) {
            throw new RuntimeException(ExceptionMessages.DATA_NOT_INITIALIZED);
        }
        SimpleSortedList<Course> sortedCourseList =
                new SimpleSortedList<>(Course.class, comparator);
        sortedCourseList.addAll(this.courses.values());
        return sortedCourseList;
    }

    @Override
    public SimpleSortedList<Student> getAllStudentsSorted(Comparator<Student> comparator) {
        if(!isDataInitialized) {
            throw new RuntimeException(ExceptionMessages.DATA_NOT_INITIALIZED);
        }
        SimpleSortedList<Student> sortedStudents =
                new SimpleSortedList<>(Student.class, comparator);
        sortedStudents.addAll(this.students.values());
        return sortedStudents;
    }

    public void filterAndTake(String courseName, String filter) {
        int studentsToTake = this.courses.get(courseName).getStudentsByName().size();
        filterAndTake(courseName, filter, studentsToTake);
    }

    public void filterAndTake(String courseName, String filter, int studentsToTake) {
        if (!isQueryForCoursePossible(courseName)) {
            return;
        }
        LinkedHashMap<String, Double> marks = new LinkedHashMap<>();
        for(Map.Entry<String, Student> entry: this.courses.get(courseName).getStudentsByName().entrySet()) {
            marks.put(entry.getKey(), entry.getValue().getMarksByCourseName().get(courseName));
        }
        this.filter.printFilteredStudents(marks, filter, studentsToTake);
    }

    public void orderAndTake(String courseName, String orderType, int studentsToTake) {
        if (!isQueryForCoursePossible(courseName)) {
            return;
        }
        LinkedHashMap<String, Double> marks = new LinkedHashMap<>();
        for(Map.Entry<String, Student> entry: this.courses.get(courseName).getStudentsByName().entrySet()) {
            marks.put(entry.getKey(), entry.getValue().getMarksByCourseName().get(courseName));
        }
        this.sorter.printSortedStudents(marks, orderType, studentsToTake);
    }

    public void orderAndTake(String courseName, String orderType) {
        int studentsToTake = this.courses.get(courseName).getStudentsByName().size();
        orderAndTake(courseName, orderType, studentsToTake);
    }

    private boolean isQueryForCoursePossible(String courseName) {
        if(!this.isDataInitialized) {
            OutputWriter.displayException(ExceptionMessages.DATA_NOT_INITIALIZED);
            return false;
        }
        if(!this.courses.containsKey(courseName)) {
            OutputWriter.displayException(ExceptionMessages.NON_EXISTING_COURSE);
            return false;
        }
        return true;
    }

    private boolean isQueryForStudentPossible(String course, String student) {
        if(!isQueryForCoursePossible(course)) {
            return false;
        }
        if(!this.courses.get(course).getStudentsByName().containsKey(student)) {
            OutputWriter.displayException(ExceptionMessages.NON_EXISTING_STUDENT);
            return false;
        }
        return true;
    }
}