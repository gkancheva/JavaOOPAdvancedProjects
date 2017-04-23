package main.com.softuni.bashsoft.repository;

import main.com.softuni.bashsoft.io.OutputWriter;
import main.com.softuni.bashsoft.static_data.ExceptionMessages;

import java.util.*;
import java.util.stream.Collectors;

public class RepositorySorter {
    private static final String ASCENDING_ORDER = "ascending";
    private static final String DESCENDING_ORDER = "descending";

    public void printSortedStudents(HashMap<String, Double> studentsMarks, String comparisonType, int nbStudents) {
        comparisonType = comparisonType.toLowerCase();
        if(!comparisonType.equals(ASCENDING_ORDER) && !comparisonType.equals(DESCENDING_ORDER)) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_COMPARISON_QUERY);
        }
        Comparator<Map.Entry<String, Double>> comparator = (x , y) -> {
            double value1 = x.getValue();
            double value2 = y.getValue();
            return Double.compare(value1, value2);
        };

        List<String> sortedStudents = studentsMarks.entrySet().stream()
                .sorted(comparator)
                .limit(nbStudents)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        if(comparisonType.equals(DESCENDING_ORDER)) {
            Collections.reverse(sortedStudents);
        }
        this.printStudents(studentsMarks, sortedStudents);
    }

    private void printStudents(HashMap<String, Double> courseData, List<String> sortedStudents) {
        for (String student : sortedStudents) {
            OutputWriter.writeMessageOnNewLine(String.format(
                    "%s - %f", student, courseData.get(student)));
        }
    }
}