package ui;

import java.util.Scanner;

import tasklist.TaskList;

/**
 * The UI class responsible for handling user interface interactions.
 * It provides methods for printing messages, reading user input, and displaying tasks.
 */
public class UI {

    /**
     * Prints the specified text to the console with an indentation.
     * @param text The text to be printed.
     */
    public static void print(Object text) {
        System.out.println("\t" + text.toString());
    }

    /**
     * Initiates the Lulu application.
     * Prints a welcome message to the user.
     */
    public static void start() {
        UI.print("Hello! I'm Lulu \n\tWhat can I do for you?");
    }

    /**
     * Exits the Lulu application.
     * Prints a goodbye message to the user.
     */
    public static void exit() {
        UI.print("Bye. Hope to see you again soon!");
    }

    /**
     * Reads the next line of input from the user.
     * @return A String representing the user's input with leading and trailing whitespaces removed.
     */
    public static String nextLine() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine().strip();
    }

    /**
     * Prints the tasks in the provided TaskList with indices.
     * @param tasks The TaskList containing tasks to be printed.
     */
    public static void printTasks(TaskList tasks) {
        UI.print("Here are the tasks in your list:");
        for (int i = 0; i < tasks.getSize(); i++) {
            String output = (i + 1) + "." + tasks.getTask(i);
            UI.print(output);
        }
    }
}
