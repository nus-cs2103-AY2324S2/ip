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
    public void showWelcome() {
        System.out.println("    -----------------------------------");
        System.out.println("    Hello! I'm bytetalker.ByteTalker");
        System.out.println("    What can I do for you?");
        System.out.println("    Supported tasks are todo, deadline, and event");
        System.out.println("    todo {task}");
        System.out.println("    deadline {task} /by {date and time}");
        System.out.println("    event {task} /from {date and time} /to {date and time}");
        System.out.println("    Supported date and time format are yyyy-MM-dd HHmm, and dd/MM/yyyy HHmm");
        System.out.println("    -----------------------------------");
    }

    /**
     * Prints out bye message.
     */
    public void showBye() {
        System.out.println("    -----------------------------------");
        System.out.println("    Bye. Hope to see you again soon!");
        System.out.println("    -----------------------------------");
    }

    /**
     * Prints out error message if there is an error while changing the task list.
     */
    public void showStoreTaskErrorMessage() {
        System.out.println("    -----------------------------------");
        System.out.println("    Error while changing the task list");
        System.out.println("    Please try again");
        System.out.println("    -----------------------------------");
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
    public void showMarkTaskMsg(Task task) {
        System.out.println("    -----------------------------------");
        System.out.println("    Nice! I've marked this task as done:");
        System.out.println("      " + task.toString());
        System.out.println("    -----------------------------------");
    }

    /**
     * Prints out the message that the task user wants is marked as undone.
     *
     * @param task Task the user wants to mark as undone.
     */
    public void showUnmarkTaskMsg(Task task) {
        System.out.println("    -----------------------------------");
        System.out.println("    OK, I've marked this task as not done yet:");
        System.out.println("      " + task.toString());
        System.out.println("    -----------------------------------");
    }

    /**
     * Prints out the message that the task user wants is deleted and prints out the length of the task
     * list.
     *
     * @param task Task the user wants to delete from the task list.
     * @param length Length of the task list after deleting the task.
     */
    public void showDeleteTaskMsg(Task task, int length) {
        System.out.println("    -----------------------------------");
        System.out.println("    Got it. I've removed this task:");
        System.out.println("        " + task.toString());
        System.out.println("    Now you have " + length + " task in the list.");
        System.out.println("    -----------------------------------");
    }

    /**
     * Prints out the message that the task user wants it added to the task list and prints out the length of the
     * task list.
     *
     * @param task Task that the user wants to add to the lsit.
     * @param length Length of the task list after adding the task.
     */
    public void showAddTaskMsg(Task task, int length) {
        System.out.println("    -----------------------------------");
        System.out.println("    Got it. I've added this task:");
        System.out.println("       " + task.toString());
        System.out.println("    Now you have " + length + " tasks in the list.");
        System.out.println("    -----------------------------------");
    }

    /**
     * Prints out the message when a file is not found in the system.
     */
    public void showFileNotFoundErrorMsg() {
        System.out.println("File is not found");
    }

    /**
     * Prints out the whole list of tasks in the list.
     *
     * @param tasks Task list that contains all the tasks added.
     */
    public void returnList(ArrayList<Task> tasks) {
        System.out.println("    -----------------------------------");
        System.out.println("    Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("    " + (i + 1) + "." + tasks.get(i).toString());
        }
        System.out.println("    -----------------------------------");
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
    public void displayFoundTasks(ArrayList<Task> foundTasks) {
        System.out.println("    -----------------------------------");
        for (int i = 0; i < foundTasks.size(); i++) {
            System.out.println("    " + (i + 1) + "." + foundTasks.get(i).toString());
        }
        if (foundTasks.size() == 0) {
            System.out.println("    No tasks found");
        }
        System.out.println("    -----------------------------------");
    }
}
