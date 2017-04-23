package main.com.softuni.bashsoft.models;

import main.com.softuni.bashsoft.contracts.Coursable;
import main.com.softuni.bashsoft.exceptions.DuplicateEntryInStructureException;
import main.com.softuni.bashsoft.exceptions.InvalidStringException;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class Course implements Coursable {
    public final static int MAX_SCORE_ON_EXAM_TASK = 5;
    public final static int NUMBER_OF_TASKS_ON_EXAM = 100;

    private String name;
    private Map<String, Student> studentsByName;

    public Course(String name) {
        this.setName(name);
        this.studentsByName = new LinkedHashMap<>();
    }

    public String getName() {
        return this.name;
    }

    private void setName(String name) {
        if(name == null || name.equals("")){
            throw new InvalidStringException();
        }
        this.name = name;
    }

    public Map<String, Student> getStudentsByName() {
        return Collections.unmodifiableMap(this.studentsByName);
    }

    public void enrollStudent(Student student) {
        if(this.studentsByName.containsKey(student.getUserName())) {
            throw new DuplicateEntryInStructureException(
                    student.getUserName(), this.name);
        }
        this.studentsByName.put(student.getUserName(), student);
    }

    @Override
    public int compareTo(Course otherCourse) {
        return this.getName().compareTo(otherCourse.getName());
    }

    @Override
    public String toString() {
        return this.getName();
    }
}
