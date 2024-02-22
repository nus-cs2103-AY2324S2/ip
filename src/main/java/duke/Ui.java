package duke;

import java.util.Scanner;

/**
 * Ui deals with interactions with the user.
 */
public class Ui {
    private Scanner scanner;

    private String input;

    /**
     * The constructor of Ui.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Display the welcome message.
     */
    public static String showWelcome() {
        String msg = "Hello! I'm Bee!\n"
                + "What can I do for you?\n";

        return msg;
    }

    /**
     * Display the goodbye message
     */
    public static String showGoodbyeMessage() {
        String str = "Bye. Hope to see you again soon!\n";
        return str;
    }

    /**
     * A getter function to get the user's input.
     *
     * @return The user's input.
     */
    public String getInput() {
        return this.input;
    }

    /**
     * To read the user's input into the chatbot.
     *
     * @param input The input of the user.
     */
    public void readCommand(String input) {
        this.input = input;
    }

}
