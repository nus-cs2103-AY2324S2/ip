package bond.main;

import java.util.ListIterator;
import java.util.Scanner;

import bond.task.Task;
import bond.task.TaskList;

/**
 * The Ui class is used to handle the user interface of the Bond task management
 * program.
 *
 * @author Benny Loh
 * @version 0.1
 */
public class Ui {

    private static final String LINE = "____________________________________________________________";
    private Scanner scanner;

    /**
     * Constructor for the Ui class.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void showLine() {
        System.out.println(Ui.LINE);
    }

    public void newLine() {
        System.out.println();
    }

    /**
     * Shows the welcome message when the program starts.
     */
    public void showWelcome() {
        System.out.println(String.format("Hello! I'm %s. \nWhat can I do for you?\n", "Bond"));
        this.showLine();
        this.newLine();
    }

    /**
     * Shows the taskList is empty message.
     */
    public void showTasklistEmpty() {
        this.showLine();
        System.out.println("\n    There are no tasks in the list.");
        this.showLine();
        this.newLine();
    }

    /**
     * Reads the user input.
     *
     * @return The user input.
     */
    public String readCommand() {
        String userInput = "";

        if (this.scanner.hasNextLine()) {
            userInput = this.scanner.nextLine();
        }

        return userInput;
    }

    /**
     * Shows the message when a task is added.
     *
     * @param newTask  The task that is added.
     * @param taskList The task list that the task is added to.
     */
    public void taskAdded(Task newTask, TaskList taskList) {
        this.showLine();
        System.out.println(String.format(
                "\n    Got it. I've added this task:\n      %s \n    Now you have %d tasks in the list.",
                newTask.toString(), taskList.numberOfTasks()));
        this.showLine();
        this.newLine();
    }

    /**
     * Shows the message when a task is deleted.
     *
     * @param deletedTask The task that is deleted.
     * @param taskList    The task list that the task is deleted from.
     */
    public void taskDeleted(Task deletedTask, TaskList taskList) {
        this.showLine();
        System.out.println(String.format(
                "\n    Got it. I've removed this task:\n      %s \n    Now you have %d tasks in the list.",
                deletedTask.toString(), taskList.numberOfTasks()));
        this.showLine();
        this.newLine();
    }

    /**
     * Shows the message when a task is marked as done.
     *
     * @param markedTask The task that is marked as done.
     * @param taskList   The task list that the task is marked as done in.
     */
    public void taskMarked(Task markedTask, TaskList taskList) {
        this.showLine();
        System.out
                .println(String.format(
                        "\n    Nice! I've marked this task as done:\n      %s",
                        markedTask.toString()));
        this.showLine();
        this.newLine();
    }

    /**
     * Shows the message when a task is marked as not done.
     *
     * @param unmarkedTask The task that is marked as not done.
     * @param taskList     The task list that the task is marked as not done in.
     */
    public void taskUnmarked(Task unmarkedTask, TaskList taskList) {
        this.showLine();
        System.out
                .println(String.format(
                        "\n    OK, I've marked this task as not done yet:\n      %s",
                        unmarkedTask.toString()));
        this.showLine();
        this.newLine();
    }

    /**
     * Shows all tasks found.
     *
     * @param taskList The task list containing all tasks found.
     */
    public void showFoundTasks(TaskList taskList) {
        ListIterator<Task> tasks = taskList.getTasks();

        this.showLine();
        System.out.println(String.format("\n    Here are the matching tasks in your list:"));

        while (tasks.hasNext()) {
            System.out.println(String.format("    %d. %s",
                    tasks.nextIndex() + 1, tasks.next().toString()));
        }

        this.showLine();
        this.newLine();
    }

    /**
     * Shows all tasks in the task list.
     *
     * @param taskList The task list to read from.
     */
    public void showList(TaskList taskList) {
        ListIterator<Task> tasks = taskList.getTasks();

        this.showLine();
        System.out.println(String.format("\n    Here are the tasks in your list:"));

        while (tasks.hasNext()) {
            System.out.println(String.format("    %d. %s",
                    tasks.nextIndex() + 1, tasks.next().toString()));
        }

        this.showLine();
        this.newLine();
    }

    /**
     * Shows the formatted error message of an exception.
     *
     * @param e The exception to be shown.
     */
    public void showError(Exception e) {
        this.showLine();
        System.out.println(String.format("\n    %s", e.getMessage()));
        this.showLine();
        this.newLine();
    }

    /**
     * Shows the goodbye message when the user exits the program.
     */
    public void showGoodbye() {
        this.showLine();
        System.out.println("\n    Bye. Hope to see you again soon!");
        this.showLine();
        this.newLine();
    }

    public void closeScanner() {
        this.scanner.close();
    }
}
