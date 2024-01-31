package johnny.ui;

import java.util.Scanner;

import johnny.exceptions.JohnnyException;
import johnny.tasks.Task;
import johnny.tasks.TaskList;

/**
 * Handles all interactions with user.
 */
public class Ui {

    /** Scanner to read input from user. */
    private Scanner scanner = new Scanner(System.in);

    /**
     * Prints error message to user.
     *
     * @param errorMessage Error message of error to be printed.
     */
    public void showError(String errorMessage) {
        System.out.println(errorMessage + "\n");
    }

    /**
     * Prints welcome message to user.
     */
    public void showWelcome() {
        System.out.println("Johnny here. What do you want bro?\n");
    }

    /**
     * Closes scanner and prints end message to user.
     */
    public void showEnd() {
        scanner.close();
        System.out.println("Bye bro. I'm going back to sleep.");
    }

    /**
     * Prints all Tasks in TaskList to user.
     *
     * @param tasks TaskList to be printed
     * @throws JohnnyException Ignore as for loop will prevent error.
     */
    public void showList(TaskList tasks) throws JohnnyException {
        if (tasks.size() == 0) {
            System.out.println("You have no tasks bro. Stop being lazy and add some tasks.\n");
        } else {
            System.out.println("Get all these done bro:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(i + 1 + ". " + tasks.get(i));
            }
            System.out.println();
        }

    }

    /**
     * Prints marked Task to user.
     *
     * @param task Task to be marked.
     */
    public void showMark(Task task) {
        System.out.println("Finally done bro.");
        System.out.println(task + "\n");
    }

    /**
     * Prints unmarked Task to user.
     *
     * @param task Task to be unmarked.
     */
    public void showUnmark(Task task) {
        System.out.println("Why are you not done yet bro?");
        System.out.println(task + "\n");
    }

    /**
     * Prints deleted Task to user.
     *
     * @param task Task to be deleted.
     */
    public void showDelete(Task task, TaskList tasks) {
        System.out.println("Task removed. Why so lazy bro?");
        System.out.println(task);
        System.out.println("You still have " + tasks.size() + " tasks in your list bro.\n");
    }

    /**
     * Prints added Task and number of Tasks in TaskList to user.
     *
     * @param task Task to be added.
     * @param tasks Tasks left in TaskList.
     */
    public void showAddTask(Task task, TaskList tasks) {
        System.out.println("Go get this done bro:");
        System.out.println(task);
        System.out.println("You still have " + tasks.size() + " tasks in your list bro.\n");
    }

    /**
     * Reads user input.
     *
     * @return User input as String.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Prints the Tasks found that matches user query.
     *
     * @param foundTasks List of Tasks that matches user query.
     * @throws JohnnyException Ignore as loop prevents error from being thrown.
     */
    public void showFoundTasks(TaskList foundTasks) throws JohnnyException {
        if (foundTasks.size() == 0) {
            System.out.println("No matches bro.\n");
        } else {
            System.out.println("All these tasks match:");
            for (int i = 0; i < foundTasks.size(); i++) {
                System.out.println(i + 1 + ". " + foundTasks.get(i));
            }
            System.out.println();
        }
    }

}
