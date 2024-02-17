package ezra;

/**
 * Represents the user interface for the Ezra task management application.
 */
public class Ui {

    /**
     * Prints a horizontal line to the console.
     */
    public static void horizontalLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays a greeting message to the user.
     */
    public static void greet() {
        Ui.horizontalLine();
        System.out.println("Hello! I'm Ezra.\nWhat can I do for you?");
        Ui.horizontalLine();
    }
}
