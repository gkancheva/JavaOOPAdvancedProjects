package main.com.softuni.bashsoft.judge;

import main.com.softuni.bashsoft.static_data.ExceptionMessages;
import main.com.softuni.bashsoft.io.OutputWriter;
import main.com.softuni.bashsoft.static_data.Messages;
import main.com.softuni.bashsoft.exceptions.InvalidPathException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Tester {
    private static final String MESSAGE_FOR_MISMATCH_STRING = "mismatch -> expected{%s}, actual{%s}%n";
    private static final String MESSAGE_FOR_MATCHED_STRING = "line match -> %s%n";
    private static final String MATCH_FILE_NAME = "\\mismatch.txt";

    public void compareContent(String actualOutput, String expectedOutput) throws IOException {
        try {
            OutputWriter.writeMessageOnNewLine(Messages.READING_FILES);
            String mismatchPath = getMismatchPath(expectedOutput);
            List<String> actualOutputString = readTextFile(actualOutput);
            List<String> expectedOutputString = readTextFile(expectedOutput);

            boolean mismatch = compareStrings(actualOutputString, expectedOutputString, mismatchPath);

            if (mismatch) {
                List<String> mismatchString = readTextFile(mismatchPath);
                mismatchString.forEach(OutputWriter::writeMessageOnNewLine);
            } else {
                OutputWriter.writeMessageOnNewLine(Messages.NO_MISMATCHES_MESSAGE);
            }
        } catch (IOException ioe) {
            throw new InvalidPathException();
        }
    }

    private List<String> readTextFile(String filePath) throws IOException {
        List<String> text = new ArrayList<>();
        File file = new File(filePath);
        FileReader fileReader = new FileReader(file);
        BufferedReader reader = new BufferedReader(fileReader);
        String line = reader.readLine();
        while (line != null) {
            text.add(line);
            line = reader.readLine();
        }
        reader.close();
        return text;
    }

    private String getMismatchPath(String expectedOutput) {
        int index = expectedOutput.lastIndexOf('\\');
        String directoryPath = expectedOutput.substring(0, index);
        return directoryPath + MATCH_FILE_NAME;
    }

    private boolean compareStrings(List<String> actualOutputString, List<String> expectedOutputString, String mismatchPath) {
        OutputWriter.writeMessageOnNewLine(Messages.COMPARING_FILES);
        String output = "";
        boolean isMismatch = false;
        try {
            FileWriter fileWriter = new FileWriter(mismatchPath);
            BufferedWriter writer = new BufferedWriter(fileWriter);

            int maxLength = Math.max(actualOutputString.size(), expectedOutputString.size());

            for (int i = 0; i < maxLength; i++) {
                String actualLine = actualOutputString.get(i);
                String expectedLine = expectedOutputString.get(i);

                if (!actualLine.equals(expectedLine)) {
                    output = String.format(MESSAGE_FOR_MISMATCH_STRING, expectedLine, actualLine);
                    isMismatch = true;
                } else {
                    output = String.format(MESSAGE_FOR_MATCHED_STRING, actualLine);
                }
                writer.write(output);
            }
            writer.close();
        } catch (IOException ioe) {
            isMismatch = true;
            OutputWriter.displayException(ExceptionMessages.CANNOT_ACCESS_FILE);
        } catch (IndexOutOfBoundsException ioobe) {
            isMismatch = true;
            OutputWriter.displayException(ExceptionMessages.INVALID_OUTPUT_LENGTH);
        }
        return isMismatch;
    }
}