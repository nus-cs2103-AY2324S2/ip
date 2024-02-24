package damon.ui;


import damon.task.Task;
import damon.tasklist.TaskList;
import damon.exceptions.StorageFileLoadingException;

import java.util.Scanner;

/**
 * Creates an Ui object to show welcome message
 * and other corresponding message to each user's input.
 */
public class Ui {
    private final Scanner scanner;

    /**
     * Constructs a new Ui object
     */
    public Ui() {
        //Solution below adapted from https://stackoverflow.com/a/16252290
        this.scanner = new Scanner(System.in);
    }


    /**
     * Prints welcome message.
     */
    public void showWelcome() {
        String logo = " ____\n"
                + "|  _  \\\n"
                + "| | | |\n"
                + "| |_| |\n"
                + "|____/ \n";
        System.out.println("Hello from\n" + logo);
    }


    /**
     * Returns user's input String.
     *
     * @return User's input.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Prints message of loading error.
     */
    public void showLoadingError() {
        System.out.println(new StorageFileLoadingException().getMessage());
    }

    /**
     * Prints divider line "____________________________________________________________".
     */
    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints message of adding a new Task to the current TaskList.
     *
     * @param newTask New Task to be added.
     * @param tasks Current TaskList.
     */
    public void showAddCommand(Task newTask, TaskList tasks) {
        System.out.println("Got it. I've added this task:\n"
                + newTask + "\n"
                + "Now you have " + tasks.getSize() + " tasks in the list.");
    }

    /**
     * Prints message of deleting a Task of a specific index in the current TaskList.
     *
     * @param index Index of the Task which is to be deleted in the current TaskList.
     * @param tasks Current TaskList.
     */
    public void showDeleteCommand(int index, TaskList tasks) {
        System.out.println("Noted. I've removed this task:\n"
                + tasks.getTask(index).toString() + "\n"
                + "Now you have " + (tasks.getSize() - 1) + " tasks in the list.");
    }

    /**
     * Prints exiting message.
     */
    public void showExitCommand() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Prints message of marking a Task of a specific index in the current TaskList
     * as done status.
     *
     * @param index Index of the Task to be marked
     *              as done status in the current TaskList.
     * @param tasks Current TaskList.
     */
    public void showMarkCommand(TaskList tasks, int index) {
        System.out.println("Nice! I've marked this task as done:\n"
                + tasks.getTask(index).toString());
    }

    /**
     * Prints message of un-marking a Task of a specific index in the current TaskList
     * to not done status.
     *
     * @param index Index of the Task to be un-marked
     *              to not done status in the current TaskList.
     * @param tasks Current TaskList.
     */
    public void showUnMarkCommand(TaskList tasks, int index) {
        System.out.println("OK, I've marked this task as not done yet:\n"
                + tasks.getTask(index).toString());
    }

    /**
     * Prints the whole TaskList
     *
     * @param tasks Current TaskList (the TaskList to be printed).
     */
    public void showShowTaskListCommand(TaskList tasks) {
        int n = tasks.getSize();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < n; i++) {
            Task currentTask = tasks.getTask(i);
            System.out.println((i + 1) + "." + currentTask.toString());
        }
    }

    /**
     * Prints message of the threw error.
     *
     * @param errorMessage Error message to be printed.
     */
    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }

    /**
     * Prints echo.
     *
     * @param inputString User's input String which is to be echoed.
     */
    public void showEchoCommand(String inputString) {
        System.out.println(inputString);
    }
}
