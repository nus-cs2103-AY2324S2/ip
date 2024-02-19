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
     * Initiates the lulu.Lulu application.
     * Prints a welcome message to the user.
     */
    public static void start() {
        UI.print("Wassup boss! I'm Lulu \n\tHow can I assist you?");
    }

    /**
     * Exits the lulu.Lulu application.
     * Prints a goodbye message to the user.
     */
    public static void exit() {
        UI.print("All-goods boss, catch you later");
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
    public static String printTasks(TaskList tasks) {
        if (tasks.getSize() == 0) {
            String output = "Naughty boy... You currently don't have any pending tasks";
            UI.print(output);
            return output;
        }
        UI.print("Here are the tasks in your list:");
        String result = "Here are the tasks in your list:\n";
        for (int i = 0; i < tasks.getSize(); i++) {
            String output = (i + 1) + "." + tasks.getTask(i);
            UI.print(output);
            result += output + "\n";
        }
        return result;
    }
}
