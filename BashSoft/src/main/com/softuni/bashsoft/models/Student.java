package main.com.softuni.bashsoft.models;

import main.com.softuni.bashsoft.contracts.Studentable;
import main.com.softuni.bashsoft.static_data.ExceptionMessages;
import main.com.softuni.bashsoft.exceptions.DuplicateEntryInStructureException;
import main.com.softuni.bashsoft.exceptions.InvalidStringException;
import main.com.softuni.bashsoft.exceptions.KeyNotFoundException;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class Student implements Studentable {
    private String userName;
    private Map<String, Course> enrolledCourse;
    private Map<String, Double> marksByCourseName;

    public Student(String userName) {
        this.setUserName(userName);
        this.enrolledCourse = new LinkedHashMap<>();
        this.marksByCourseName = new LinkedHashMap<>();
    }

    public String getUserName() {
        return userName;
    }

    public Map<String, Double> getMarksByCourseName() {
        //return new LinkedHashMap<>(this.marksByCourseName);
        return Collections.unmodifiableMap(this.marksByCourseName);
    }

    private void setUserName(String userName) {
        if(userName == null || userName.equals("")) {
            throw new InvalidStringException();
        }
        this.userName = userName;
    }

    public void enrollInCourse(Course course) {
        if(this.enrolledCourse.containsKey(course.getName())) {
            throw new DuplicateEntryInStructureException(
                    this.userName, course.getName());
        }
        this.enrolledCourse.put(course.getName(), course);
    }

    public void setMarkOnCourse(String courseName, int... scores) {
        if(!this.enrolledCourse.containsKey(courseName)) {
            throw new KeyNotFoundException();
        }
        if(scores.length > Course.NUMBER_OF_TASKS_ON_EXAM) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_NUMBER_OF_SCORES);
        }
        double mark = calculateMark(scores);
        this.marksByCourseName.put(courseName, mark);
    }

    public String getMarkForCourse(String courseName) {
        String output = String.format("%s - %f",
                this.userName, marksByCourseName.get(courseName));
        return output;
    }

    private double calculateMark(int[] scores) {
        double percentageOfSolvedExam = Arrays.stream(scores).sum() /
                (double)(Course.NUMBER_OF_TASKS_ON_EXAM * Course.MAX_SCORE_ON_EXAM_TASK);
        return percentageOfSolvedExam * 4 + 2;
    }

    @Override
    public int compareTo(Student otherStudent) {
        return this.getUserName().compareTo(otherStudent.getUserName());
    }

    @Override
    public String toString() {
        return this.getUserName();
    }
}
