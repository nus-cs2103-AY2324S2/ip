package duke;

import java.util.Scanner;

/**
 * The Ui class is responsible for handling user interactions.
 * It provides methods to display messages to the user and parse user input.
 */
public class Ui {
    private Scanner scanner;

    /**
     * Constructs a Ui object, initializing a scanner to read user input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Prints one or more messages to the console, each separated by a line.
     *
     * @param messages An array of messages to be printed.
     */
    public static void printWithLines(String... messages) {
        System.out.println("------------------------------------------");
        for (String message : messages) {
            System.out.println(message);
        }
    }

    /**
     * Displays an error message when there is an error loading the file.
     */
    public static void showLoadingError() {
        printWithLines("Error loading file");
    }

    /**
     * Parses and processes the user input string.
     * It delegates the command handling to the Parser class.
     *
     * @param list    The TaskList to be used for executing commands.
     * @param message The user input string.
     */
    public static String parse(TaskList list, String message) throws DukeException {
        try {
            if (message.startsWith("todo")) {
                return Parser.handleTodo(list, message);
            } else if (message.startsWith("deadline")) {
                return Parser.handleDeadline(list, message);
            } else if (message.startsWith("event")) {
                return Parser.handleEvent(list, message);
            } else if (message.equals("list")) {
                return Parser.handleList(list);
            } else if (message.startsWith("mark")) {
                return Parser.handleMark(list, message);
            } else if (message.startsWith("unmark")) {
                return Parser.handleUnmark(list, message);
            } else if (message.startsWith("delete")) {
                return Parser.deleteTask(list, message);
            } else if (message.startsWith("find")) {
                return Parser.findTask(list, message);
            } else if (message.equals("bye")) {
                // Assuming you have some logic to handle the "bye" command
                return "Bye! Hope to see you again soon!";
            } else {
                throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means buddy.");
            }
        } catch (DukeException e) {
            // Use the message from the caught exception
            throw e;
        }
    }

}
