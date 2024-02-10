package lai.util;

import lai.task.Task;

import java.util.Scanner;

/**
 * Handles the user interface for the Lai application.
 * This class is responsible for printing messages to the console
 * and reading user input.
 */
public class Ui {
    protected Scanner scanner;

    /**
     * Constructs a UI object with a given Scanner.
     *
     * @param scanner The scanner to read user input.
     */
    public Ui (Scanner scanner) {
        this.scanner = scanner;
    }

    /**
     * Prints a welcome message at the start of the application.
     */
    public static void printStart() {
        System.out.println("Hi there, I am Lai. Your friendly fairly useless chatbot.");
        System.out.println("What can I assist you with today?");
        printLine();
    }

    /**
     * Prints a farewell message when exiting the application.
     */
    public static void printBye() {
        printLine();
        System.out.println("Goodbye, we shall meet again. Hopefully.");
        printLine();
    }

    /**
     * Prints a decorative line to separate sections of output for better readability.
     */
    public static void printLine() {
        System.out.println("---------------------------------------------------------");
    }

    /**
     * Prints a message indicating a task has been marked as done.
     *
     * @param t The task that was marked as done.
     */
    public static void printTaskMarked(Task t) {
        printLine();
        System.out.println("You actually did something? Marked done:");
        System.out.println(t);
        printLine();
    }

    /**
     * Prints a message indicating a task has been marked as not done.
     *
     * @param t The task that was marked as not done.
     */
    public static void printTaskUnmarked(Task t) {
        printLine();
        System.out.println("Come on now, don't be useless. Marked not done:");
        System.out.println(t);
        printLine();
    }

    /**
     * Prints a message indicating a new task has been added.
     *
     * @param newTask The task that was added.
     * @param tasks   The current list of tasks, for displaying the total count.
     */
    public static void printTaskAdded(Task newTask, TaskList tasks) {
        printLine();
        System.out.println("Added: " + newTask);
        System.out.println(String.format("Total number of tasks: %s", tasks.size()));
        printLine();
    }

    /**
     * Prints a message indicating a task has been deleted.
     *
     * @param t The task that was deleted.
     */
    public static void printTaskDeleted(Task t) {
        printLine();
        System.out.println("I have deleted:");
        System.out.println(t);
        printLine();
    }

    /**
     * Lists all tasks currently in the task list.
     *
     * @param tasks The task list to be displayed.
     */
    public static void listTasks(TaskList tasks) {
        printLine();
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(String.format("%s. %s", i + 1, tasks.get(i)));
        }
        printLine();
    }

    /**
     * Prints an exception message specific to the Lai application.
     *
     * @param e The LaiException to be printed.
     */
    public static void printLaiException(LaiException e) {
        printLine();
        System.out.println(e);
        printLine();
    }

    /**
     * Prints a user-friendly message when a NumberFormatException occurs.
     *
     * @param e The NumberFormatException that was caught.
     */
    public static void printNumberFormatException(NumberFormatException e) {
        printLine();
        System.out.println("Error occurred: Numbers only, please.");
        printLine();
    }

    /**
     * Prompts the user for input and returns the parsed command and arguments.
     *
     * @return A string array containing the command and its arguments.
     */
    public String[] getInput() {
        System.out.print("> ");
        return Parser.parse(scanner);
    }
}
