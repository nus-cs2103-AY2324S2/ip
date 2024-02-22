
package ui;

import java.util.Scanner;

import task.TaskList;
import task.Task;

import exception.XiaoBaiException;

public class Ui {
    Scanner reader;

    public Ui() {
        this.reader = new Scanner(System.in);
    }

    /**
     * Displays a farewell message.
     */
    public String showExitMessage() {
        StringBuilder outputString = new StringBuilder();
        outputString.append("Bye. Hope to see you again soon!");
        return outputString.toString();
    }

    public String showErrorMessage(String errorMessage) {
        StringBuilder outputString = new StringBuilder();
        outputString.append(errorMessage);
        return outputString.toString();
    }

    /**
     * Displays a message of the deleted task and the number of task in the list
     * 
     * @param task
     * @param listOfTasks
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
     * Displays a repeated message of the input by the user and number of task in
     * list.
     * 
     * @param task        Task input
     * @param listOfTasks List of all tasks
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
     * Marks task as done.
     * 
     * @param task
     */
    public String showMarkMessage(Task task) {
        StringBuilder outputString = new StringBuilder();
        outputString.append("Nice! I've marked this task as done:\n");
        outputString.append(task.toString());
        return outputString.toString();
    }

    /**
     * Marks task as not done.
     * 
     * @param task
     */
    public String showUnmarkMessage(Task task) {
        StringBuilder outputString = new StringBuilder();
        outputString.append("OK, I've marked this task as not done yet:\n");
        outputString.append(task.toString());
        return outputString.toString();
    }

    /**
     * Displays the list of Strings.
     * 
     * @param listOfStrings list of Strings.
     */
    public String showPrintListMessage(TaskList taskList) {
        StringBuilder outputString = new StringBuilder();
        StringBuilder finalString = new StringBuilder();
        finalString.append("Here are the tasks in your list:\n");
        int counter = 1;
        for (Task c : taskList.getList()) {
            finalString.append(String.format("%d. %s\n", counter, c.toString()));
            counter++;
        }
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