package main.com.softuni.bashsoft.repository;

import main.com.softuni.bashsoft.io.OutputWriter;
import main.com.softuni.bashsoft.exceptions.InvalidFilter;

import java.util.Map;
import java.util.function.Predicate;

public class RepositoryFilter {
    private static final String POOR_MARK = "poor";
    private static final String AVERAGE_MARK = "average";
    private static final String EXCELLENT_MARK = "excellent";

    public void printFilteredStudents(Map<String, Double> studentsWithMarks,
                                      String filterType, Integer numberOfStudents) {

        Predicate<Double> filter = createFilter(filterType);
        if(filter == null) {
            throw new InvalidFilter();
        }
        int studentsCount = 0;
        for (String student : studentsWithMarks.keySet()) {
            if(studentsCount >= numberOfStudents) {
                break;
            }
            double mark = studentsWithMarks.get(student);
            if(filter.test(mark)) {
                OutputWriter.writeMessageOnNewLine(String.format(
                        "%s - %f", student, mark));
                studentsCount++;
            }
        }
    }

    private Predicate<Double> createFilter(String filterType) {
        switch (filterType) {
            case EXCELLENT_MARK:
                return mark -> mark >= 5.0;
            case AVERAGE_MARK:
                return mark -> 3.5 <= mark && mark < 5.0;
            case POOR_MARK:
                return mark -> mark < 3.5;
            default:
                return null;
        }
    }

}