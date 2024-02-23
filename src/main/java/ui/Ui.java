
package ui;

import java.util.Scanner;

import task.TaskList;
import task.Task;

import exception.XiaoBaiException;

/**
 * Represents the user interface of the XiaoBai chatbot application.
 * Handles input and output interactions with the user.
 */
public class Ui {
    Scanner reader;

    /**
     * Constructs a Ui object with a Scanner to read user input from the console.
     */
    public Ui() {
        this.reader = new Scanner(System.in);
    }

    /**
     * Displays a farewell message.
     *
     * @return A string representing the farewell message.
     */
    public String showExitMessage() {
        StringBuilder outputString = new StringBuilder();
        outputString.append("Bye. Hope to see you again soon!");
        return outputString.toString();
    }

    /**
     * Displays an error message.
     *
     * @param e The XiaoBaiException indicating the error.
     * @return The error message as a string.
     */
    public String showErrorMessage(XiaoBaiException e) {
        return e.toString();
    }

    /**
     * Displays a message confirming the deletion of a task.
     *
     * @param task     The task that was deleted.
     * @param taskList The updated task list after deletion.
     * @return The deletion confirmation message as a string.
     */
    public String showDeleteMessage(Task task, TaskList taskList) {
        StringBuilder outputString = new StringBuilder();
        outputString.append("Noted. I've removed this task:\n");
        outputString.append(task.toString() + "\n");
        int len = taskList.getSize();
        outputString.append(String.format("Now you have %d tasks in the list.", len));
        return outputString.toString();
    }

    /**
     * Displays a message confirming the addition of a task.
     *
     * @param task     The task that was added.
     * @param taskList The updated task list after addition.
     * @return The addition confirmation message as a string.
     */
    public String showAddTaskMessage(Task task, TaskList taskList) {
        StringBuilder outputString = new StringBuilder();
        outputString.append("Got it. I've added this task:\n");
        outputString.append(task.toString() + "\n");
        int len = taskList.getSize();
        outputString.append(String.format("Now you have %d tasks in the list.", len));
        return outputString.toString();
    }

    /**
     * Generates a message displaying the sorted list of tasks.
     *
     * @param taskList The TaskList containing the sorted list of tasks.
     * @return A formatted string displaying the sorted list of tasks.
     */
    public String showSortMessage(TaskList taskList) {
        StringBuilder outputString = new StringBuilder();
        StringBuilder finalString = new StringBuilder();
        finalString.append("Here is the sorted list:\n");
        int counter = 1;
        for (Task c : taskList.getList()) {
            finalString.append(String.format("%d. %s\n", counter, c.toString()));
            counter++;
        }
        outputString.append(finalString.toString());
        return outputString.toString();
    }

    /**
     * Generates a message indicating that the specified task has been marked as
     * done.
     * If the task is already marked as done, the message reflects this status.
     *
     * @param task The task to be marked as done.
     * @return A string message indicating the status of the task after marking.
     */
    public String showMarkMessage(Task task) {
        StringBuilder outputString = new StringBuilder();
        if (task.getIsDone()) {
            outputString.append("This task has already been marked as done:\n");
        } else {
            outputString.append("WOOF! I've marked this task as done:\n");
        }
        outputString.append(task.toString());
        return outputString.toString();
    }

    /**
     * Generates a message indicating that the specified task has been marked as not
     * done.
     * If the task is already marked as not done, the message reflects this status.
     *
     * @param task The task to be marked as not done.
     * @return A string message indicating the status of the task after marking.
     */
    public String showUnmarkMessage(Task task) {
        StringBuilder outputString = new StringBuilder();
        if (task.getIsDone()) {
            outputString.append("WOOF, I've marked this task as not done yet:\n");
        } else {
            outputString.append("This task has already been marked as not done:\n");
        }
        outputString.append(task.toString());
        return outputString.toString();
    }

    /**
     * Displays the list of tasks in the task list.
     *
     * @param taskList The TaskList containing the list of tasks to be displayed.
     * @return A formatted string displaying the list of tasks.
     */
    public String showPrintListMessage(TaskList taskList) {
        StringBuilder outputString = new StringBuilder();
        StringBuilder finalString = new StringBuilder();
        int counter = 1;
        for (Task c : taskList.getList()) {
            finalString.append(String.format("%d. %s\n", counter, c.toString()));
            counter++;
        }
        outputString.append(String.format("Here are the %s tasks in your list:\n", counter - 1));
        outputString.append(finalString.toString());
        return outputString.toString();
    }

    /**
     * Finds tasks in the given task list that contain the specified string.
     * Displays the matching tasks along with their indices.
     *
     * @param taskList   The task list to search in.
     * @param findString The string to search for in task names.
     */
    public String showFoundTask(TaskList taskList, String findString) {
        StringBuilder outputString = new StringBuilder();
        StringBuilder finalString = new StringBuilder();
        finalString.append("Here are the matching tasks in your list:\n");
        int counter = 1;
        for (Task c : taskList.getList()) {
            if (c.getName().contains(findString)) {
                finalString.append(String.format("%d. %s\n", counter, c.toString()));
                counter++;
            }
        }
        outputString.append(finalString.toString());
        return outputString.toString();
    }

    /**
     * Displays an error message when there is an issue loading data into Duke.
     *
     * @param e The DukeException indicating the loading error.
     */
    public void showLoadingError(XiaoBaiException e) {
        StringBuilder outputString = new StringBuilder();
        outputString.append(e.toString() + "\n");
        System.out.println(outputString.toString());
    }
}