package badgpt.util;

import badgpt.exceptions.BadException;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Handles interaction with user. Prints all messages and responses and takes in user input.
 */
public class Ui {
    protected Scanner sc;

    /**
     * Initialises the Scanner object to take in user input. Also sets console and error output to a specified output
     * stream to be read by the GUI.
     *
     * @param out The stream for console output.
     * @param err The stream for error output.
     */
    public Ui(ByteArrayOutputStream out, ByteArrayOutputStream err) {
        sc = new Scanner(System.in);
        System.setOut(new PrintStream(out));
        System.setErr(new PrintStream(err));
    }

    /**
     * Prints a line to make the chatbot look nice.
     */
    public void printLine() {
        System.out.println("_____________________________________________________");
    }

    /**
     * Prints any exceptions thrown by the bot.
     *
     * @param e The exception thrown by the bot.
     */
    public void printException(BadException e) {
        System.err.println("_____________________________________________________\n"
                + e + "\n_____________________________________________________");
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
