package atsisbot;

import java.util.Scanner;

import atsisbot.task.Task;
import atsisbot.task.TaskList;

/**
 * The Ui class represents the user interface of the ATSISBot application.
 * It provides methods for reading user commands, printing messages, and
 * displaying task lists.
 */
public class Ui {
    private static final String WELCOME_MESSAGE = "Hello! I'm atsisbot.AtsisBot\n" + "What can I do for you?\n";
    private static final String ENDING_MESSAGE = "Bye. Hope to see you again soon!\n";
    private static final String LIST_MESSAGE = "Here are the tasks in your list:\n";
    private static final String TASK_AS_DONE = "Nice! I've marked this atsisbot.task as done:\n";
    private static final String TASK_AS_NOT_DONE_YET = "OK, I've marked this atsisbot.task as not done yet:\n";
    private static final String ADDED_THIS_ATSISBOT_TASK = "Got it. I've added this atsisbot.task:\n";
    private static final String REMOVED_THIS_ATSISBOT_TASK = "Noted. I've removed this atsisbot.task:\n";
    private static final String NO_DESCRIPTION_MESSAGE = "The description of a %s cannot be empty.\n";
    private static final String INVALID_DEADLINE_FORMAT_MESSAGE = "Invalid deadline format. Please use:"
        + " deadline <description> /by <date>\n";
    private static final String INVALID_EVENT_FORMAT_MESSAGE = "Invalid event format. Please use:"
        + " event <description> /from <date> /to <date>\n";
    private static final String INVALID_TASK_NUMBER_MESSAGE = "Invalid atsisbot.task number."
        + " Please enter a valid atsisbot.task number.\n";
    private static final String INVALID_INPUT_FORMAT_MESSAGE = "Invalid input format. Please use the correct format.\n";
    private static final String UNKNOWN_COMMAND_MESSAGE = "I'm sorry, but I don't understand that command.";
    private static final String INVALID_FIND_MESSAGE = "No matching tasks found.\n";
    private static final String FIND_MESSAGE = "Here are the matching tasks in your list:\n";
    private static final String INVALID_PRIORITY_FORMAT_MESSAGE = "Invalid priority format. "
            + "Please use the correct format.\n";
    private static final String DONE_SET_PRIORITY = "Nice! I've set the priority for this task:\n";
    private static Scanner sc = new Scanner(System.in);

    /**
     * Reads a command from the user.
     *
     * @return the command entered by the user as a String.
     */
    public static String readCommand() {
        return sc.nextLine();
    }

    /**
     * Prints the contents of a TaskList.
     *
     * @param list The TaskList to be printed.
     */
    public static void printList(TaskList list) {
        System.out.print(LIST_MESSAGE + list.getList());
    }

    /**
     * Prints the welcome message to the console.
     */
    public static void printWelcomeMessage() {
        System.out.println(WELCOME_MESSAGE);
    }

    /**
     * Prints the ending message to the console.
     */
    public static void printEndingMessage() {
        System.out.println(ENDING_MESSAGE);
    }

    /**
     * Prints the mark message for a given task.
     *
     * @param task The task to print the mark message for.
     */
    public static void printMarkMessage(Task task) {
        System.out.print(TASK_AS_DONE + task.toString());
    }

    /**
     * Prints the unmark message for a given task.
     *
     * @param task The task to be unmarked.
     */
    public static void printUnmarkMessage(Task task) {
        System.out.print(TASK_AS_NOT_DONE_YET + task.toString());
    }

    /**
     * Prints a message indicating that a task has been added to the task list.
     *
     * @param task     The task that has been added.
     * @param taskList The task list containing the added task.
     */
    public static void printAddTaskMessage(Task task, TaskList taskList) {
        System.out.println(ADDED_THIS_ATSISBOT_TASK + "  " + task.toString() + taskList.getSize());
    }

    /**
     * Prints a message indicating that a task has been deleted.
     *
     * @param task     The task that has been deleted.
     * @param taskList The task list containing the deleted task.
     */
    public static void printDeleteTaskMessage(Task task, TaskList taskList) {
        System.out.println(REMOVED_THIS_ATSISBOT_TASK + "  " + task.toString() + taskList.getSize());
    }

    /**
     * Prints the message for an invalid task number.
     */
    public static void printInvalidTaskNumberMessage() {
        System.out.println(INVALID_TASK_NUMBER_MESSAGE);
    }

    /**
     * Prints the unknown command message.
     */
    public static void printUnknownCommandMessage() {
        System.out.println(UNKNOWN_COMMAND_MESSAGE);
    }

    /**
     * Prints a message indicating that a task of the specified type does not have a
     * description.
     *
     * @param taskType the type of task
     */
    public static void printNoDescriptionMessage(String taskType) {
        System.out.print(String.format(NO_DESCRIPTION_MESSAGE, taskType));
    }

    /**
     * Prints the message for an invalid deadline format.
     */
    public static void printInvalidDeadlineFormatMessage() {
        System.out.println(INVALID_DEADLINE_FORMAT_MESSAGE);
    }

    /**
     * Prints the invalid event format message.
     */
    public static void printInvalidEventFormatMessage() {
        System.out.println(INVALID_EVENT_FORMAT_MESSAGE);
    }

    /**
     * Closes the scanner used for user input.
     */
    public static void closeScanner() {
        sc.close();
    }

    /**
     * Prints the invalid input format message.
     */
    public static void printInvalidInputFormatMessage() {
        System.out.println(INVALID_INPUT_FORMAT_MESSAGE);
    }

    /**
     * Prints the invalid priority format message to the console.
     * This method is typically called when the user enters a priority in an incorrect format.
     */
    public static void printInvalidPriorityFormatMessage() {
        System.out.println(INVALID_PRIORITY_FORMAT_MESSAGE);
    }

    /**
     * Prints the message indicating that the priority for a task has been set.
     * This method is typically called after the priority of a task has been successfully updated.
     *
     * @param task The task for which the priority has been set.
     */
    public static void printSetPriorityMessage(Task task) {
        System.out.print(DONE_SET_PRIORITY + task.toString());
    }

    /**
     * Prints the find message.
     * If the message is empty, it prints an invalid find message.
     * Otherwise, it prints the find message followed by the given message.
     *
     * @param message the message to be printed
     */
    public static void printFindMessage(String message) {
        if (message.equals("")) {
            System.out.print(INVALID_FIND_MESSAGE);
            return;
        }
        System.out.print(FIND_MESSAGE + message);
    }
}
