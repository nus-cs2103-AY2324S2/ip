package bytetalker.ui;

import bytetalker.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Deals with interactions with the user. Mainly the print statements are included in this class to control the
 * messages.
 *
 * @author Junseo Kim
 * @version 0.1
 * @since 2024-02-06
 */
public class Ui {
    /**
     * Prints out welcome message and command format the user can use to enter the task.
     */
    public String showWelcome() {
        String welcomeMessage = "Hello! I'm bytetalker.ByteTalker\n" + "What can I do for you?\n" + "Supported tasks " +
                "are todo, deadline, and event\n" + "todo {task}\n" + "deadline {task} /by {date and time}\n" +
                "event {task} /from {date and time} /to {date and time}\n" + "Supported date and time format are " +
                "yyyy-MM-dd HHmm, and dd/MM/yyyy HHmm";
        return welcomeMessage;
    }

    /**
     * Prints out bye message.
     */
    public String showBye() {
        String byeString =
                "    Bye. Hope to see you again soon!";
        return byeString;
    }

    /**
     * Prints out error message if there is an error while changing the task list.
     */
    public String showStoreTaskErrorMessage() {
        String storeTaskErrorMessage = "Error while changing the task list\n" + "Please try again";
        return storeTaskErrorMessage;
    }

    /**
     * Accepts user input and returns it.
     *
     * @return String User input.
     */
    public String storeUserInput() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    /**
     * Prints out the message that the task user wants is marked as done.
     *
     * @param task Task that user wants to mark as done.
     */
    public String showMarkTaskMsg(Task task) {
        String markedTaskMessage = "Nice! I've marked this task as done:\n" + "      " + task.toString();
        return markedTaskMessage;
    }

    /**
     * Prints out the message that the task user wants is marked as undone.
     *
     * @param task Task the user wants to mark as undone.
     */
    public String showUnmarkTaskMsg(Task task) {
        String unmarkedTaskMessage = "OK, I've marked this task as not done yet:\n" + "      " + task.toString();
        return unmarkedTaskMessage;
    }

    /**
     * Prints out the message that the task user wants is deleted and prints out the length of the task
     * list.
     *
     * @param task Task the user wants to delete from the task list.
     * @param length Length of the task list after deleting the task.
     */
    public String showDeleteTaskMsg(Task task, int length) {
        String deletedTaskMessage = "Got it. I've removed this task:" + "\n" + "        " + task.toString() + "\n" +
                "Now you have " + length + " task in the list.";
        return deletedTaskMessage;
    }

    /**
     * Prints out the message that the task user wants it added to the task list and prints out the length of the
     * task list.
     *
     * @param task Task that the user wants to add to the lsit.
     * @param length Length of the task list after adding the task.
     */
    public String showAddTaskMsg(Task task, int length) {
        String addedTaskMessage = "Got it. I've added this task:" + "\n" + "       " + task.toString() + "\n" + "Now " +
                "you have " + length + " tasks in the list.";
        return addedTaskMessage;
    }

    /**
     * Prints out the message when a file is not found in the system.
     */
    public String showFileNotFoundErrorMsg() {
        return "File is not found";
    }

    /**
     * Prints out the whole list of tasks in the list.
     *
     * @param tasks Task list that contains all the tasks added.
     */
    public String returnList(ArrayList<Task> tasks) {
        String taskListMessage = "";
        taskListMessage += "Here are the tasks in your list:\n";
        for (int i = 0; i < tasks.size(); i++) {
            taskListMessage += "    " + (i + 1) + "." + tasks.get(i).toString() + "\n";
        }
        return taskListMessage;
    }

    /**
     * Prints out the error message when date and time parse cannot be done.
     *
     * @param e Error occured while parsing date and time.
     */
    public static void showDateTimeParseErrorMsg(Exception e) {
        System.err.println("Unable to parse the date and time string: " + e.getMessage());
    }

    /**
     * Prints out the list of tasks found based on the user input.
     *
     * @param foundTasks List of tasks found.
     */
    public String displayFoundTasks(ArrayList<Task> foundTasks) {
        String foundTasksMessage = "Here are the found tasks:\n";
        for (int i = 0; i < foundTasks.size(); i++) {
            foundTasksMessage += (i + 1) + "." + foundTasks.get(i).toString() + "\n";
        }
        if (foundTasks.size() == 0) {
            return "No tasks found";
        }
        return foundTasksMessage;
    }
}
