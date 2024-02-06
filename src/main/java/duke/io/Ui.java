package duke.io;
import duke.task.Task;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Ui - Handles interactions with the user, including input and output.
 */
public class Ui {
    private static final String LINE_SEPARATOR = "____________________________________________________________";

    /**
     * Displays the welcome message.
     */
    public static void showWelcomeMessage() {
        System.out.println("Hello! I'm RATZCHAT");
        System.out.println("How can I help you today?");
        printLine();
    }

    /**
     * Displays the goodbye message.
     */
    public static void showByeMessage() {
        System.out.println("BYEBYE. Thank you for using RATZCHAT!");
        printLine();
    }

    /**
     * Displays an error message.
     *
     * @param message The error message to be displayed.
     */
    public static void showErrorMessage(String message) {
        System.out.println("Error: " + message);
        printLine();
    }

    /**
     * Prints a line separator.
     */
    public static void printLine() {
        System.out.println(LINE_SEPARATOR);
    }

    public static String getUserInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    /**
     * Retrieves user input from the console.
     *
     * @return The user's input as a String.
     */
    public static void showTaskList(ArrayList<Task> tasks) {
        System.out.println("These are your to-dos: ");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
        printLine();
    }

    /**
     * Displays the list of tasks.
     *
     * @param task ArrayList of tasks to be displayed.
     */
    public static void showMarkedAsDone(Task task) {
        System.out.println("I've marked this task as done:\n  " + task);
    }

    /**
     * Displays a message indicating that a task has been marked as done.
     *
     * @param task The task that has been marked as done.
     */
    public static void showUnmarkedTask(Task task) {
        System.out.println("I've unmarked this task! It is now not done yet:\n  " + task);
    }

    /**
     * Displays a message indicating that a task has been unmarked.
     *
     * @param task The task that has been unmarked.
     */
    public static void showTaskAdded(Task task, int totalTasks) {
        System.out.println("Ok! I've added this task: " + task);
        System.out.println("Now you have " + totalTasks + " tasks in your list.");
    }

    /**
     * Displays a message indicating that a task has been added.
     *
     * @param removedTask       The task that has been added.
     * @param totalTasks The total number of tasks after the addition.
     */
    public static void showTaskRemoved(Task removedTask, int totalTasks) {
        System.out.println("Ok! I have removed this task from your list:\n  " + removedTask);
        System.out.println("Now you have " + totalTasks + " tasks in your list.");
    }

    public static void showFindItemList(String keyword) {
        System.out.println("Items containing '" + keyword + "':");
    }
}