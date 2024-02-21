package duke;

import duke.task.Task;
import java.util.Scanner;

/**
 * Encapsulates the way the user can interact with the chatbot.
 */
public class Ui {
    /**
     * Reads the command that the user type.
     *
     * @return The input from the user.
     */
    public static String readCommand() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    /**
     * Prints welcome message to the user.
     */
    public static String showWelcomeMessage() {
        return "Hello! I'm Blob.\n" + "What can I do for you?";
    }

    /**
     * Prints an error message that tells that the user types in the wrong format.
     */
    public static String showWrongFormat() {
        return "Wrong format! :(";
    }

    /**
     * Prints out all the Tasks that the user has.
     *
     * @param tasks The user's TaskList
     */
    public static String showTaskList(TaskList tasks) {
        System.out.println("Here are the tasks in your list:");
        System.out.println(tasks);
        System.out.println();
        return "Here are the tasks in your list:\n" + tasks;
    }

    /**
     * Prints an error message that tells that the user forgets to type the task number.
     */
    public static String showForgetTaskNumber() {
        return "You forgot to type which task!";
    }

    /**
     * Prints the correct format for setting a task as Done.
     */
    public static String showMarkFormat() {
        return "Type: 'mark n' to mark the n-th task.\n" +
                "For example type: 'mark 1' to mark the first task.";
    }

    /**
     * Prints the correct format for setting a task as Undone.
     */
    public static String showUnmarkFormat() {
        return "Type: 'unmark n' to unmark the n-th task.\n" + "For example, type: 'unmark 1' to mark the first task.";
    }

    /**
     * Prints the correct format for deleting a task.
     */
    public static String showDeleteFormat() {
        return "Type: 'delete n' to delete the n-th task.\n" +
                "For example type: 'delete 1' to delete the first task.";
    }

    /**
     * Prints the correct format for typing in a date.
     */
    public static String showDateFormat() {
        return "The correct date format is: YYYY-MM-DD";
    }

    /**
     * Prints the correct format for creating a ToDo task.
     */
    public static String showToDoFormat() {
        return "The correct format is:\n" + "todo <description>";
    }

    /**
     * Prints the correct format for creating a Deadline Task.
     */
    public static String showDeadlineFormat() {
        return "The correct format is:\n" +
                "deadline <description> /by <deadline time>";
    }


    /**
     * Prints the correct format of find command.
     */
    public static String showFindFormat() {
        return "The correct format is:\n" + "find <keyWord>";
    }

    /**
     * Prints the correct format for creating an Event task.
     */
    public static String showEventFormat() {
        return "The correct format is:\n" +
                "event <description> /from <start time> /to <end time>";
    }

    /**
     * Shows all the valid commands to the user.
     */
    public static String showValidCommands() {
        return "You need to use 'todo', 'deadline' or 'event' command to add a task.\n" +
                "You can use 'list' to see all of your tasks.\n" +
                "Use 'mark' or 'unmark' for any of your tasks.";
    }


    public static String showMatchingTasks(String matchingTasks) {
        return "Here are the matching tasks in your list:\n" + matchingTasks;
    }

    /**
     * Tells the user that a task has been successfully added.
     *
     * @param task The task that has been added to the list.
     * @param taskListSize The size of the list after the task has been added.
     */
    public static String showCreateTask(Task task, int taskListSize) {
        return "Got it. I've added this task:\n" + task + "\nNow you have " + taskListSize + " tasks in the list.";
    }

    /**
     * Tells the user that the task number they inputted does not exist.
     */
    public static String showNoTaskFound() {
        return "You don't have that task!";
    }

    /**
     * Tells the user that a task has been successfully set as Done.
     *
     * @param task The task that has been set as Done.
     */
    public static String showMarkTask(Task task) {
        return "Nice! I've marked this task done:\n" + task;
    }

    /**
     * Tells the user that a task has been successfully set as Undone.
     *
     * @param task The task that has been set as Undone.
     */
    public static String showUnmarkTask(Task task) {
        return "OK, I've marked this task as not done yet:\n" + task;
    }

    /**
     * Tells the user that a task has been successfully deleted.
     *
     * @param task The task that has been deleted.
     * @param taskListSize The size of the list after the task has been deleted.
     */
    public static String showDeleteTask(Task task, int taskListSize) {
        return "Noted. I've removed this task:\n" + task +
                "Now you have " + taskListSize + " tasks in the list.";
    }

    /**
     * Prints the exit message to the user.
     */
    public static String showExitMessage() {
        return "Bye. Hope to see you again soon!";
    }
}
