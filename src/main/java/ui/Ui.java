package ui;

/**
 * Deaks with interactions with the user
 */
public class Ui {
    /**
     * Constructor is not needed
     */
    private Ui() {
        throw new AssertionError("Constructor is not allowed");
    }

    /**
     * Print out welcome speech
     */
    public static void welcomeText() {
        System.out.println("___________________________________________");
        System.out.println("Hello! I'm Taylor");
        System.out.println("What can I do for you?");
    }

    /**
     * Print out exiting speech
     */
    public static void goodbyeText() {
        System.out.println("___________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Print out the error message
     * @param err Error thrown from ChatBot
     */
    public static void printError(Exception err) {
        System.out.println("Error: " + err);
    }

    /**
     * Print warning for invalid commands from Users
     */
    public static void invalidCommand() {
        System.out.println("Invalid input. ChatBot can only handle "
                + "'todo', 'deadline', 'event', 'bye', 'list' tasks");
    }

    /**
     * Print out error for blank User input
     */
    public static void blankCommand() {
        System.out.println("Input is empty, please enter something.");
    }
}
