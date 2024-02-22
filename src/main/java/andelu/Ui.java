package andelu;

import java.util.Scanner;

/**
 * A class for user interaction.
 */
public class Ui {
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

    public String getWelcomeStatement() {
        return "Hello! I'm AndrewOng2066.\nWhat can I do for you?";
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



    public void printInvalidFeature() {
        System.out.println("      I'm sorry, I do not understand that.");
    }


    public void printLoadingError() {
        System.out.println("    Unable to load the data from the file.");
    }

    public void printError(String e) {
        System.out.println("    " + e);
    }

}
