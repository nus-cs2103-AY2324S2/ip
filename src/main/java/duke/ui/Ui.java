package duke.ui;

import duke.task.Task;

import java.util.Scanner;


/**
 * Contains functions that handle the user interface of the chatbot.
 */
public class Ui {
    private Scanner scanner;

    /**
     * Constructs a Ui object with a new Scanner instance.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays the welcome message when the application starts.
     */
    public void showWelcomeMessage() {
        System.out.println("Hello! I'm Cookie");
        System.out.println("What can I do for you?");
    }

    /**
     * Displays the goodbye message when the application is terminated by user.
     */
    public void showByeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Displays a message upon the addition of a new task.
     *
     * @param t The task that was added.
     * @param counter The current count of tasks.
     */
    public void showAddTaskMessage(Task t, int counter) {
        System.out.println("Got it. I've added this task:");
        System.out.println(t.toString());
        System.out.println("Now you have " + (counter + 1) + " tasks in the list.");
    }

    /**
     * Displays a message when a task is deleted.
     *
     * @param t       The task that was removed.
     * @param counter The current count of tasks.
     */
    public void showRemoveTaskMessage(Task t, int counter) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(t.toString());
        System.out.println("Now you have " + (counter - 1) + " tasks in the list.");
    }

    /**
     * Displays a message confirming that a task has been marked as done.
     *
     * @param t The task that has been marked as done.
     */
    public void showMarkTaskDoneMessage(Task t) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(t.toString());
    }

    /**
     * Displays a generic message.
     *
     * @param message The message to be displayed.
     */
    public void showMessage(String message) {
        System.out.println(message);
    }

    /**
     * Prints the list of tasks.
     *
     * @param tasks An array of tasks to be displayed.
     * @param counter The current count of tasks.
     */
    public void showTaskList(Task[] tasks, int counter) {
        for (int i = 1; i <= counter; i++) {
            Task task = tasks[i - 1];
            String taskDesc = task.toString();
            System.out.println(i + "." + taskDesc);
        }
    }
}
