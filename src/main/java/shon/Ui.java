package shon;

import java.util.Scanner;

/**
 * Represents the user interface that reads user inputs and outputs information to user.
 */
public class Ui {
    private Scanner scanner;

    /**
     * Creates a new Ui that reads user input from standard input and outputs to standard output.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Outputs a formatted preset greeting to standard output.
     */
    public void greet() {
        print("Hello! I'm Shon", "What can I do for you?");
    }

    /**
     * Outputs a formatted preset goodbye to standard output.
     */
    public void exit() {
        print("Bye. Hope to see you again soon!");
        this.scanner.close();
    }

    /**
     * Reads one line of user input through standard input.
     *
     * @return The given user input, stripped of white spaces.
     */
    public String readInput() {
        String input = this.scanner.nextLine();
        return input.strip();
    }

    /**
     * Formats and outputs the given text. If messages is received as an array of String, each String in the array will
     * be printed out on a separate line.
     *
     * @param messages The text to be formatted and output. Varargs is used, messages could be a single String,
     * multiple Strings, or an array of Strings.
     */
    public void print(String... messages) {
        String line = "    _______________" +
                "_____________________________________________";
        System.out.println(line);
        for (String msg : messages) {
            System.out.println("     " + msg);
        }
        System.out.println(line);
        System.out.println();
    }
}
