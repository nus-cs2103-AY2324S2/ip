package bytebuddy.ui;

import static bytebuddy.constants.Information.SOLID_LINE_BREAK;
import static bytebuddy.constants.Messages.BYE_MESSAGE;
import static bytebuddy.constants.Messages.START_MESSAGE;

import java.util.Scanner;

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
        System.out.println("\t" + SOLID_LINE_BREAK);
        System.out.println(s);
        System.out.println("\t" + SOLID_LINE_BREAK);
    }

    /**
     * Prints the start message with a solid line break above and below it.
     */
    public static void printStartMessage() {
        printWithSolidLineBreak("\t " + START_MESSAGE);
    }

    /**
     * Prints the bye message with a solid line break above and below it.
     */
    public static void printByeMessage() {
        printWithSolidLineBreak("\t " + BYE_MESSAGE);
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

