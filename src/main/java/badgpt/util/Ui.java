package badgpt.util;

import badgpt.exceptions.BadException;

import java.util.Scanner;

/**
 * Handles interaction with user. Prints all messages and responses and takes in user input.
 */
public class Ui {
    private Scanner sc;

    /**
     * Initialises the Scanner object to take in user input.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Prints a line to make the chatbot look nice.
     */
    public void printLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints any exceptions thrown by the bot.
     *
     * @param e The exception thrown by the bot.
     */
    public void printException(BadException e) {
        System.err.println("____________________________________________________________\n"
                + e + "\n____________________________________________________________");
    }

    /**
     * Prints a greeting.
     *
     * @param name The name of the bot.
     */
    public void sayHi(String name) {
        printLine();
        System.out.println("Hello! I'm " + name + ".\n" + "What can I do for you?");
        printLine();
    }

    /**
     * Prints a farewell.
     */
    public void sayBye() {
        printLine();
        System.out.println("Smell ya later");
        printLine();
    }

    /**
     * Reads the next line of user input.
     */
    public String read() {
        return sc.nextLine();
    }

    /**
     * Closes the scanner.
     */
    public void stop() {
        sc.close();
    }
}
