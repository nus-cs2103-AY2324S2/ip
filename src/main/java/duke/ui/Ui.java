package duke.ui;

import duke.dukeexception.DukeException;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * Read the inputs from the user and sends the outputs to the user.
 */
public class Ui {

    /** The input source from the user. */
    private final Scanner in;
    /** The output source to the user. */
    private final PrintStream out;

    /**
     * Constructs the Ui object that will facilitate the interaction between the user and chatbot.
     */
    public Ui() {
        in = new Scanner(System.in);
        out = System.out;
    }

    /**
     * Prints the welcome message to the user.
     */
    public void greet() {
        out.println("Hello! I'm " + "NotDuke");
        out.println("What can I do for you?");
    }

    /**
     * Returns the string inputted by the user into the input source.
     * @return the user's input string
     */
    public String getUserCommand() {
        String fullInputLine = in.nextLine();
        return fullInputLine;
    }

    /**
     * Prints the outputMessage to the output source.
     * @param outputMessage The output message to be printed to the user
     */
    public void sendReply(String outputMessage) {
        out.print(outputMessage);
    }

    /**
     * Prints the error message to the output source
     * @param e The error that has occurred
     */
    public void showError(DukeException e) {
        out.println(e);
    }
}
