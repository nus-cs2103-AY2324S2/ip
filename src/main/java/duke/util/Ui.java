package duke.util;

import duke.task.Task;

import java.io.InputStream;
import java.util.Scanner;

/**
 * Represents the mechanism of a user interface.
 */
public class Ui {
    private static final String LINE = "\t________________________________________________________________";

    private String name;
    private String logo;
    private Scanner scanner;

    public Ui(String name, String logo, InputStream inputStream) {
        this.name = name;
        this.logo = logo;
        this.scanner = new Scanner(inputStream);
    }

    /**
     * Displays welcome message.
     */
    public void showWelcome() {
        System.out.println(LINE);
        System.out.println(logo);
        System.out.println("\t Hello! I'm " + this.name + ".");
        System.out.println("\t What can I do for you?\n" + LINE);
    }

    /**
     * Reads command using Scanner.
     *
     * @return String read from InputStream.
     */
    public String readCommand() {
        System.out.println();
        return scanner.nextLine();
    }

    /**
     * Displays line.
     */
    public void showLine() {
        System.out.println(LINE);
    }

    /**
     * Displays error message.
     *
     * @param errorMessage Details about the error to be displayed.
     */
    public void showError(String errorMessage) {
        System.out.println("\t OOPS!!! " + errorMessage);
    }

    /**
     * Displays error message due to reading from file.
     */
    public void showLoadingError() {
        System.out.println("\t OOPS!!! Error loading tasks from file.");
    }

    /**
     * Displays notification on successful mark operation.
     *
     * @param task Task that was marked as done.
     */
    public void showMarked(Task task) {
        System.out.println("\t Nice! I've marked this task as done:");
        System.out.println("\t\t " + task.printTask());
    }

    /**
     * Displays notification on successful unmark operation.
     *
     * @param task Task that was marked as not done.
     */
    public void showUnmarked(Task task) {
        System.out.println("\t Ok, I've marked this task as not done yet:");
        System.out.println("\t\t " + task.printTask());
    }

    /**
     * Displays notification on successful add operation.
     *
     * @param t Task that was added.
     * @param list Holds all tasks added.
     */
    public void showAdded(Task t, TaskList list) {
        int size = list.getSize();
        System.out.println("\t Got it! I've added this task:\n\t\t " + t.printTask());
        System.out.println("\t Now you have " + size + (size > 1 ? " tasks" : " task") + " in the list.");
    }

    /**
     * Displays all tasks in list.
     * Outputs message if list is empty.
     *
     * @param list Holds all tasks added
     */
    public void showTasks(TaskList list) {
        if (list.getSize() == 0) {
            System.out.println("\t OOPS!!! No task in list.");
            System.out.println("\t You may add task with keywords: todo, deadline, event.");
        } else {
            System.out.println(list.print());
        }
    }

    /**
     * Displays notification on successful delete operation.
     *
     * @param t Task that was deleted.
     * @param list Holds all tasks remaining.
     */
    public void showDeleted(Task t, TaskList list) {
        System.out.println("\t Noted I've removed this task:");
        System.out.println("\t\t " + t.printTask());
        System.out.println("\t Now you have " + list.getSize() + (list.getSize() > 1 ? " tasks" : " task") + " in the list.");
    }

    /**
     * Displays exit message.
     */
    public void showGoodbye() {
        System.out.println("\t Bye. Hope to see you again soon!");
    }
}
