package duke;
public class ErrorHandler {

    public static void handleFileNotFoundException(String filePath) {
        System.out.println("File not found: " + filePath);
    }

    public static void handleIOException() {
        System.out.println("An error occurred while accessing the file.");
    }

    public static void handleNumberFormatException(String input) {
        System.out.println("Invalid number format: " + input);
    }

}
