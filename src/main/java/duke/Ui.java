package duke;

import java.util.Scanner;

/**
 * Represents the part of the program that handles user interaction
 */
class Ui {
    private final String name;

    /**
     * Initializes the Ui with the name given by the user.
     *
     * @param name The name of the bot
     */
    Ui(String name) {
        this.name = name;
    }

    /**
     * Sends a greeting message.
     */
    void greeting() {
        System.out.println("Hello! I'm " + name);
        System.out.println("What can I do for you?");
        separatePrompt();
    }

    /**
     * Reads in a user prompt and sends it to the Parser.
     *
     * @return the String representing the user prompt.
     */
    String readCommand() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    /**
     * Separates the previously sent messages and starts a new one.
     */
    void separatePrompt() {
        System.out.println("_____________________________________________________________________________");
    }
}
