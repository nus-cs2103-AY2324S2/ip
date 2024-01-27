package com.example.artemis;

import java.util.ArrayList;

/**
 * Represents the user interface for the Artemis application.
 */
public class Ui {

    /**
     * Displays a welcome message to the user.
     */
    public void showWelcomeMessage() {
        showLine();
        System.out.println("     Hello! I'm Artemis");
        System.out.println("     What can I do for you?");
        showLine();
    }

    /**
     * Displays a goodbye message to the user.
     */
    public void showGoodbyeMessage() {
        showLine();
        System.out.println("     Bye. Hope to see you again soon!");
        showLine();
    }

    /**
     * Displays an error message related to loading tasks.
     */
    public void showLoadingError() {
        showLine();
        System.out.println("     Error loading tasks from file.");
        showLine();
    }

    /**
     * Displays an error message to the user.
     *
     * @param message The error message to be displayed.
     */
    public void showError(String message) {
        showLine();
        System.out.println("     " + message);
        showLine();
    }

    /**
     * Displays the list of tasks to the user.
     *
     * @param tasks The list of tasks to be displayed.
     */
    public void showTaskList(ArrayList<Task> tasks) {
        showLine();
        System.out.println("     Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); ++i) {
            System.out.println("     " + (i + 1) + "." + tasks.get(i));
        }
        showLine();
    }

    /**
     * Displays a message indicating a task has been marked as done.
     *
     * @param task The task that has been marked as done.
     */
    public void showTaskMarkedAsDone(Task task) {
        showLine();
        System.out.println("     Nice! I've marked this task as done:");
        System.out.println("       " + task);
        showLine();
    }

    /**
     * Displays a message indicating a task has been marked as not done.
     *
     * @param task The task that has been marked as not done.
     */
    public void showTaskMarkedAsNotDone(Task task) {
        showLine();
        System.out.println("     OK, I've marked this task as not done yet:");
        System.out.println("       " + task);
        showLine();
    }

    /**
     * Displays a message indicating a new task has been added.
     *
     * @param size The total number of tasks after the addition.
     * @param task The task that has been added.
     */
    public void showTaskAdded(int size, Task task) {
        showLine();
        System.out.println("     Got it. I've added this task:");
        System.out.println("       " + task);
        System.out.println("     Now you have " + size + " tasks in the list.");
        showLine();
    }

    /**
     * Displays a message indicating a task has been deleted.
     *
     * @param task  The task that has been deleted.
     * @param index The total number of tasks after the deletion.
     */
    public void showTaskDelete(Task task, int index) {
        showLine();
        System.out.println("     Noted. I've removed this task:");
        System.out.println("       " + task);
        System.out.println("     Now you have " + index + " tasks in the list.");
        showLine();
    }

    /**
     * Displays a horizontal line separator.
     */
    public void showLine() {
        System.out.println("    ____________________________________________________________");
    }
}
