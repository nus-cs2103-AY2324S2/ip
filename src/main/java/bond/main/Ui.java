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

    public String showLine() {
        System.out.println(Ui.LINE);
        return Ui.LINE;
    }

    public String newLine() {
        System.out.println();
        return System.lineSeparator();
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
    public String showTasklistEmpty() {
        String message = "\n    There are no tasks in the list.";
        StringBuilder builder = new StringBuilder();
        builder.append(this.showLine());
        builder.append(message);
        System.out.println(message);
        builder.append(this.showLine());
        builder.append(this.newLine());
        return message;
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
    public String taskAdded(Task newTask, TaskList taskList) {
        String message = String.format(
                "\n    Got it. I've added this task:\n      %s \n    Now you have %d tasks in the list.",
                newTask.toString(), taskList.numberOfTasks());
        StringBuilder builder = new StringBuilder();
        builder.append(this.showLine());
        builder.append(message);
        System.out.println(message);
        builder.append(this.showLine());
        builder.append(this.newLine());
        return message;
    }

    /**
     * Shows the message when a task is deleted.
     *
     * @param deletedTask The task that is deleted.
     * @param taskList    The task list that the task is deleted from.
     */
    public String taskDeleted(Task deletedTask, TaskList taskList) {
        String message = String.format(
                "\n    Got it. I've removed this task:\n      %s \n    Now you have %d tasks in the list.",
                deletedTask.toString(), taskList.numberOfTasks());
        StringBuilder builder = new StringBuilder();
        builder.append(this.showLine());
        builder.append(message);
        System.out.println(message);
        builder.append(this.showLine());
        builder.append(this.newLine());
        return message;
    }

    /**
     * Shows the message when a task is marked as done.
     *
     * @param markedTask The task that is marked as done.
     * @param taskList   The task list that the task is marked as done in.
     */
    public String taskMarked(Task markedTask, TaskList taskList) {
        String message = String.format(
                "\n    Nice! I've marked this task as done:\n      %s",
                markedTask.toString());
        StringBuilder builder = new StringBuilder();
        builder.append(this.showLine());
        builder.append(message);
        System.out.println(message);
        builder.append(this.showLine());
        builder.append(this.newLine());
        return message;
    }

    /**
     * Shows the message when a task is marked as not done.
     *
     * @param unmarkedTask The task that is marked as not done.
     * @param taskList     The task list that the task is marked as not done in.
     */
    public String taskUnmarked(Task unmarkedTask, TaskList taskList) {
        String message = String.format(
                "\n    OK, I've marked this task as not done yet:\n      %s",
                unmarkedTask.toString());
        StringBuilder builder = new StringBuilder();
        builder.append(this.showLine());
        builder.append(message);
        System.out.println(message);
        builder.append(this.showLine());
        builder.append(this.newLine());
        return message;
    }

    /**
     * Shows all tasks found.
     *
     * @param taskList The task list containing all tasks found.
     */
    public String showFoundTasks(TaskList taskList) {
        String message = String.format("\n    Here are the matching tasks in your list:");
        StringBuilder builder = new StringBuilder();
        builder.append(this.showLine());
        builder.append(message);
        System.out.println(message);

        ListIterator<Task> tasks = taskList.getTasks();

        while (tasks.hasNext()) {
            String foundTask = String.format("    %d. %s",
                    tasks.nextIndex() + 1, tasks.next().toString());
            builder.append(foundTask);
            System.out.println(foundTask);
        }

        builder.append(this.showLine());
        builder.append(this.newLine());

        return builder.toString();
    }

    /**
     * Shows all tasks in the task list.
     *
     * @param taskList The task list to read from.
     */
    public String showList(TaskList taskList) {
        String message = "\n    Here are the tasks in your list:";
        StringBuilder builder = new StringBuilder();
        builder.append(this.showLine());
        builder.append(message);
        System.out.println(message);

        ListIterator<Task> tasks = taskList.getTasks();

        while (tasks.hasNext()) {
            String task = String.format("    %d. %s",
                    tasks.nextIndex() + 1, tasks.next().toString());
            builder.append(task);
            System.out.println(task);
        }

        builder.append(this.showLine());
        builder.append(this.newLine());

        return builder.toString();
    }

    /**
     * Shows the formatted error message of an exception.
     *
     * @param e The exception to be shown.
     */
    public String showError(Exception e) {
        String message = String.format("\n    %s", e.getMessage());
        StringBuilder builder = new StringBuilder();
        builder.append(this.showLine());
        builder.append(message);
        System.out.println(message);
        builder.append(this.showLine());
        builder.append(this.newLine());
        return message;
    }

    /**
     * Shows the goodbye message when the user exits the program.
     */
    public String showGoodbye() {
        String message = "\n    Bye. Hope to see you again soon!";
        StringBuilder builder = new StringBuilder();
        builder.append(this.showLine());
        builder.append(message);
        System.out.println(message);
        builder.append(this.showLine());
        builder.append(this.newLine());
        return message;
    }

    public void closeScanner() {
        this.scanner.close();
    }
}
