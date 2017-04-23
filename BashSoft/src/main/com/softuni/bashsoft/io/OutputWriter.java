package main.com.softuni.bashsoft.io;

public class OutputWriter {
    public static void writeMessage(String message) {
        System.out.printf("%s", message);
    }

    public static void writeMessageOnNewLine(String message) {
        System.out.println(message);
    }

    public static void writeEmptyLine() {
        System.out.println();
    }

    public static void displayException(String message) {
        System.out.print("\u001B[31m" + message + "\u001B[0m");
    }
}
