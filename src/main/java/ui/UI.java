package ui;

import tasklist.TaskList;

import java.util.Scanner;

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