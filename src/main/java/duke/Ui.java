package duke;

import java.util.Scanner;

/**
 * A class for user interaction.
 */
public class Ui {

    private String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);

    /**
     * A constructor to create new Ui Object.
     */
    public Ui() {

    }

    /**
     * Prints the welcome statements.
     */
    public void printWelcome() {
        System.out.println("    ____________________________________________________________");
        System.out.println("      Hello! I'm AndrewOng2066");
        System.out.println("      What can I do for you?");
        System.out.println("    ____________________________________________________________\n");
    }

    /**
     * Prints the exit statement.
     */
    public void printByeStatement() {
        System.out.println("      Bye. Hope to see you again soon!");
    }

    /**
     * Prints any statement.
     *
     * @param input
     */
    public void printAnyStatement(String input) {
        System.out.println("    " + input);
    }

    /**
     * Reads the user input.
     *
     * @return the user input.
     */
    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine().trim();
    }


    /**
     * Prints a row of dotted lines.
     */
    public void printOpeningDottedLine() {
        System.out.println("    ____________________________________________________________");
    }


    /**
     * Prints a row of dotted lines with line separator at the back.
     */
    public void printClosingDottedLine() {
        System.out.println("    ____________________________________________________________\n");
    }

    /**
     * Prints when none of the feature is selected.
     */
    public void invalidFeature() {
        System.out.println("      I'm sorry, I do not understand that.");
    }

    /**
     * Prints an error about the loading of data from the file.
     */
    public void showLoadingError() {
        System.out.println("    Unable to load the data from the file.");
    }


    /**
     * Prints any error message.
     *
     * @param e the error message to be printed.
     */
    public void showError(String e) {
        System.out.println("    " + e);
    }

}
