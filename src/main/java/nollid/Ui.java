package nollid;

import java.util.Scanner;

/**
 * Ui class handles user interface-related functionality, such as sending messages and reading user input.
 */
public class Ui {
    /**
     * Default length of the line to be printed.
     */
    public static final int LINE_LENGTH = 30;

    /**
     * Sends a welcome message upon starting the bot, with horizontal lines
     * printed for visual separation.
     */
    public void sendWelcomeMessage() {
        String welcomeMessage = "Hello! I'm Nollid.\n" + "What can I do for you?";
        sendMessage(welcomeMessage);
    }

    /**
     * Formats message that the bot will send.
     *
     * @param message The message for the bot to send.
     */
    public void sendMessage(String message) {
        printHorizontalLine(LINE_LENGTH);
        System.out.println(message);
        printHorizontalLine(LINE_LENGTH);
    }

    /**
     * Sends a goodbye message upon exiting the bot, with horizontal lines
     * printed for visual separation.
     */
    public void sendGoodbyeMessage() {
        String goodbyeMessage = "Bye. Hope to see you again soon!";
        sendMessage(goodbyeMessage);
    }

    /**
     * Prints a horizontal line with unicode character U+2500.
     *
     * @param length Length of line in characters.
     */
    public void printHorizontalLine(int length) {
        for (int i = 0; i < length; i++) {
            System.out.print("─");
        }
        System.out.println();
    }

    /**
     * Returns a String of the user's input.
     *
     * @param scanner The Scanner object used for reading user input.
     * @return The user's input as a string.
     */
    public String readCommand(Scanner scanner) {
        return scanner.nextLine().strip();
    }
}
