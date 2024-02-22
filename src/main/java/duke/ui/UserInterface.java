package duke.ui;

import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {
    public static final String INDENT = "     ";

    public static final String LINE = "____________________________________________________________";

    private static final String WELCOME_MESSAGE = String.join(
            "\n",
            "Hello! I'm DEREK",
            INDENT + "What can I do for you?");

    private static final String EXIT_MESSAGE = "Bye. Hope to see you again soon!";

    private static final Scanner scanner = new Scanner(System.in);

    public static String getUserInput() {
        return scanner.nextLine();
    }

    public static void print(String msg) {
        System.out.println(INDENT + LINE);
        String[] lines = msg.split("\n");
        System.out.println(INDENT + lines[0]);
        for (int i = 1; i < lines.length; i++) {
            System.out.println(INDENT + lines[i]);
        }
        System.out.println(INDENT + LINE + "\n");
    }

    public static void printWelcome() {
        System.out.println(INDENT + LINE);
        System.out.println(INDENT + WELCOME_MESSAGE);
        System.out.println(INDENT + LINE + "\n");
    }

    public static void printExit() {
        scanner.close();
        System.out.println(INDENT + LINE);
        System.out.println(INDENT + EXIT_MESSAGE);
        System.out.println(INDENT + LINE + "\n");
    }

    public static void printTaskAdded(String msg, int numTasks) {
        System.out.println(INDENT + LINE);
        System.out.println(INDENT + "Added Task:");
        System.out.println(INDENT + "  " + msg);
        System.out.println(INDENT + "Now you have " + numTasks + " tasks in the list.");
        System.out.println(INDENT + LINE + "\n");
    }

    public static void printTaskDeleted(String msg, int numTasks) {
        System.out.println(INDENT + LINE);
        System.out.println(INDENT + "Deleted Task:");
        System.out.println(INDENT + "  " + msg);
        System.out.println(INDENT + "Now you have " + numTasks + " tasks in the list.");
        System.out.println(INDENT + LINE + "\n");
    }

    public static void printTasksByIndices(ArrayList<String> taskRepresentations) {
        System.out.println(INDENT + LINE);
        if (taskRepresentations.size() == 0) {
            System.out.println(INDENT + "No matching tasks found.");
        } else {
            System.out.println(INDENT + "Here are the matching tasks in your list:");
            for (String t : taskRepresentations) {
                System.out.println(INDENT + t);
            }
        }
        System.out.println(INDENT + LINE + "\n");
    }

    public static void showError(String error) {
        System.out.println(INDENT + LINE);
        String[] lines = error.split("\n");
        System.out.println(INDENT + "Error: " + lines[0]);
        for (int i = 1; i < lines.length; i++) {
            System.out.println(INDENT + lines[i]);
        }
        System.out.println(INDENT + LINE + "\n");
    }
}
