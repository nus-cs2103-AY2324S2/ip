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
    private static final String line = "____________________________________________________________\n";
    private static final String welcomeMessage = line + "Hello! I'm atsisbot.AtsisBot\n" + "What can I do for you?\n"
            + line;
    private static final String endingMessage = line + "Bye. Hope to see you again soon!\n" + line;
    private static final String listMessage = "Here are the tasks in your list:\n";
    private static final String markMessage = "Nice! I've marked this atsisbot.task as done:\n";
    private static final String unmarkMessage = "OK, I've marked this atsisbot.task as not done yet:\n";
    private static final String addTaskMessage = "Got it. I've added this atsisbot.task:\n";
    private static final String deleteTaskMessage = "Noted. I've removed this atsisbot.task:\n";
    private static final String noDescriptionMessage = "The description of a %s cannot be empty.\n";
    private static final String invalidDeadlineFormatMessage = "Invalid deadline format. Please use:"
        + " deadline <description> /by <date>\n";
    private static final String invalidEventFormatMessage = "Invalid event format. Please use:"
        + " event <description> /from <date> /to <date>\n";
    private static final String invalidTaskNumberMessage = "Invalid atsisbot.task number."
        + " Please enter a valid atsisbot.task number.\n";
    private static final String invalidInputFormatMessage = "Invalid input format. Please use the correct format.\n";
    private static final String unknownCommandMessage = "I'm sorry, but I don't understand that command.";
    private static final String invalidFindMessage = "No matching tasks found.\n";
    private static final String findMessage = "Here are the matching tasks in your list:\n";

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
        System.out.print(listMessage + list.getList());
    }

    /**
     * Prints the welcome message to the console.
     */
    public static void printWelcomeMessage() {
        System.out.println(welcomeMessage);
    }

    /**
     * Prints the ending message to the console.
     */
    public static void printEndingMessage() {
        System.out.println(endingMessage);
    }

    /**
     * Prints the mark message for a given task.
     *
     * @param task The task to print the mark message for.
     */
    public static void printMarkMessage(Task task) {
        System.out.print(markMessage + task.toString());
    }

    /**
     * Prints the unmark message for a given task.
     *
     * @param task The task to be unmarked.
     */
    public static void printUnmarkMessage(Task task) {
        System.out.print(unmarkMessage + task.toString());
    }

    /**
     * Prints a message indicating that a task has been added to the task list.
     *
     * @param task     The task that has been added.
     * @param taskList The task list containing the added task.
     */
    public static void printAddTaskMessage(Task task, TaskList taskList) {
        System.out.println(addTaskMessage + "  " + task.toString() + taskList.getSize());
    }

    /**
     * Prints a message indicating that a task has been deleted.
     *
     * @param task     The task that has been deleted.
     * @param taskList The task list containing the deleted task.
     */
    public static void printDeleteTaskMessage(Task task, TaskList taskList) {
        System.out.println(deleteTaskMessage + "  " + task.toString() + taskList.getSize());
    }

    /**
     * Prints the message for an invalid task number.
     */
    public static void printInvalidTaskNumberMessage() {
        System.out.println(invalidTaskNumberMessage);
    }

    /**
     * Prints the unknown command message.
     */
    public static void printUnknownCommandMessage() {
        System.out.println(unknownCommandMessage);
    }

    /**
     * Prints a message indicating that a task of the specified type does not have a
     * description.
     *
     * @param taskType the type of task
     */
    public static void printNoDescriptionMessage(String taskType) {
        System.out.print(String.format(noDescriptionMessage, taskType));
    }

    /**
     * Prints the message for an invalid deadline format.
     */
    public static void printInvalidDeadlineFormatMessage() {
        System.out.println(invalidDeadlineFormatMessage);
    }

    /**
     * Prints the invalid event format message.
     */
    public static void printInvalidEventFormatMessage() {
        System.out.println(invalidEventFormatMessage);
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
        System.out.println(invalidInputFormatMessage);
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
            System.out.print(invalidFindMessage);
            return;
        }
        System.out.print(findMessage + message);
    }

    public static void printLine() {
        System.out.println(line);
    }
}
