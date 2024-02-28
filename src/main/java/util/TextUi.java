package util;

import java.util.Scanner;

/**
 * This TextUi class provides an abstraction of the printing of messages.
 * Handles printing of the replies for the main application,
 * allow us to have a centralized location to format these messages.
 */
public class TextUi {
    private Scanner textReader;

    /**
     * Creates a TextUi object.
     * Will start the java.util.Scanner for the application.
     */
    public TextUi() {
        this.textReader = new Scanner(System.in);
    }

    /**
     * Reads the next user input with the scanner created.
     * Returns the input.
     */
    public String readNextLine() {
        return this.textReader.nextLine();
    }

    /**
     * Prints the messages to the user.
     * Formats the message slightly by adding an end-line
     * String after printing the message.
     *
     * @param messages The message to be printed.
     */
    public void printMessage(String messages) {
        System.out.println(messages);
        System.out.println(Messages.MESSAGE_END_LINE);
    }

    /**
     * Closes the scanner.
     */
    public void closeReader() {
        this.textReader.close();
    }
}
