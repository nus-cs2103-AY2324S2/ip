package bytebuddy.ui;

import java.util.Scanner;

import static bytebuddy.constants.Information.solidLineBreak;
import static bytebuddy.constants.Messages.BYE_MESSAGE;
import static bytebuddy.constants.Messages.START_MESSAGE;

/**
 * The Ui class handles user interface interactions, including printing messages and reading user input.
 */
public class Ui {
    private Scanner sc;

    /**
     * Creates a new Ui instance, initializing the Scanner for user input.
     */
    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Prints the given string with a solid line break above and below it.
     *
     * @param s The string to be printed.
     */
    public static void printWithSolidLineBreak(String s) {
        System.out.println("\t" + solidLineBreak);
        System.out.println("\t " + s);
        System.out.println("\t" + solidLineBreak);
    }

    /**
     * Prints the start message with a solid line break above and below it.
     */
    public static void printStartMessage() {
        printWithSolidLineBreak(START_MESSAGE);
    }

    /**
     * Prints the bye message with a solid line break above and below it.
     */
    public static void printByeMessage() {
        printWithSolidLineBreak(BYE_MESSAGE);
    }

    /**
     * Reads a command entered by the user.
     *
     * @return The command entered by the user.
     */
    public String readCommand() {
        return sc.nextLine();
    }
}

