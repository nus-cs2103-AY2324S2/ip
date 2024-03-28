package ui;

import tasks.Task;

/**
 * Ui class handles the user interface of the application.
 * It is responsible for reading user input and printing messages to the user.
 */
public class Ui {


    /*
     * Constructor for Ui class.
     * It initializes the scanner object to read user input.
     * It also prints a greeting message to the user.
     */
    public Ui() {}


    /**
     * Displays a message indicating a task has been added.
     *
     * @param task The task that has been added.
     */
    public String triggerAddMessage(Task task) {
        return "Added task:\n" + task;
    }

    /**
     * Displays a message indicating a task has been deleted.
     *
     * @param task The task that has been deleted.
     */
    public String triggerDeleteMessage(Task task) {
        return "Okay, I'll stop yapping about this task:\n" + task;
    }
}
