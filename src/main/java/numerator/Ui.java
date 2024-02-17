package numerator;

import numerator.exceptions.NumeratorException;
import numerator.exceptions.storage.LoadingException;

/**
 * Handles the user interface of the application.
 */
public class Ui {

    /**
     * Prints a line separator.
     */
    public static void printLine() {
        String line = "____________________________________________________________";
        System.out.println(line);
    }


    /**
     * Prints the logo of the application.
     */
    public static void printLogo() {
        String logo = "Hello! I'm Numerator\n" + "What can I do for you?";
        System.out.println(logo);
    }

    /**
     * Prints the error message when an exception occurs while loading the data.
     *
     * @param e the exception that occurred.
     */
    public static void printLoadingError(LoadingException e) {
        System.out.println(e.getMessage());
    }

    /**
     * Prints the error message when an exception occurs.
     *
     * @param e the exception that occurred.
     */
    public static void printError(NumeratorException e) {
        System.out.println(e.getMessage());
    }

    /**
     * Prints a message.
     *
     * @param message the message to be printed.
     */
    public static void printMessage(String message) {
        System.out.println(message);
    }

    /**
     * Prints the exit message.
     */
    public static void printExit() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
