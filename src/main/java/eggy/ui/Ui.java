package eggy.ui;

import java.util.List;
import java.util.Scanner;

import eggy.task.Task;
import eggy.task.TaskList;

/**
 * The user interface of the chatbot.
 */
public class Ui {
    /** The divider line. */
    private static final String DIVIDER = "    ____________________________________________________________";
    /** The scanner to read user input. */
    private final Scanner sc = new Scanner(System.in);

    /**
     * Reads the next line of user input.
     *
     * @return The next line of user input.
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Prints the divider line.
     */
    public void printDivider() {
        System.out.println(DIVIDER);
    }

    /**
     * Prints the welcome message.
     *
     * @param name The name of the chatbot.
     */
    public void printWelcome(String name) {
        this.printDivider();
        System.out.println("     Hello! I'm " + name + "\uD83E\uDD5A.");
        System.out.println("     What can I do for you?");
        this.printDivider();
    }

    /**
     * Prints the goodbye message.
     */
    public void printGoodbye() {
        System.out.println("     Bye\uD83D\uDC4B. Hope to see you again soon!");
        sc.close();
    }

    /**
     * Prints the task added message.
     *
     * @param task The task added.
     * @param taskCount The number of tasks in the task list.
     */
    public void printTaskAdded(Task task, int taskCount) {
        System.out.println("     Got it. I've added this task:");
        System.out.println("       " + task.toString());
        System.out.println("     Now you have " + taskCount + " tasks in the list.");
    }

    /**
     * Prints the task removed message.
     *
     * @param task The task removed.
     * @param taskCount The number of tasks in the task list.
     */
    public void printTaskRemoved(Task task, int taskCount) {
        System.out.println("     Noted. I've removed this task:");
        System.out.println("       " + task.toString());
        System.out.println("     Now you have " + taskCount + " tasks in the list.");
    }

    /**
     * Prints the task marked as done message.
     *
     * @param task The task marked as done.
     */
    public void printTaskMarkedDone(Task task) {
        System.out.println("     Nice! I've marked this task as done:\n       " + task.toString());
    }

    /**
     * Prints the task marked as not done message.
     *
     * @param task The task marked as not done.
     */
    public void printTaskUnmarkedDone(Task task) {
        System.out.println("     OK, I've marked this task as not done yet:\n       " + task.toString());
    }

    /**
     * Prints the task list.
     *
     * @param tasks The task list.
     */
    public void printTaskList(TaskList tasks) {
        System.out.println("     Here are the tasks in your list:");
        System.out.println(tasks.toString());
    }

    /**
     * Prints the task list empty message.
     */
    public void printTaskListEmpty() {
        System.out.println("     There are no tasks in your list.");
    }

    /**
     * Prints the matching tasks.
     *
     * @param tasks The matching tasks.
     */
    public void printMatchingTasks(List<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("     There are no matching tasks in your list.");
        } else {
            System.out.println("     Here are the matching tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println("     " + (i + 1) + "." + tasks.get(i));
            }
        }
    }

    /**
     * Prints the exception message.
     *
     * @param message The exception message.
     */
    public void printException(String message) {
        System.out.println("     " + message);
    }
}
