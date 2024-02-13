package lite.util;

import lite.task.Task;
import lite.task.TaskList;

public class Printer {

    /**
     * Prints a horizontal line for formatting
     */
    public static void printHorizontalLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints a message notifying that a task has been removed
     * @param tasks List of tasks
     * @param index Index of task to remove from
     */
    public static void removeTask(TaskList tasks, int index) {
        System.out.println("Noted. I've removed this lite.task:\n" +
                tasks.get(index) + "\n" +
                "Now you have " + (tasks.size() - 1) + " tasks in the tasks.");
    }

    /**
     * Prints an error message when delete does not meet the input format
     * @param tasks List of tasks
     */
    public static void deleteException(TaskList tasks) {
        System.out.println("Please input a valid index. \n"
                + "The correct format is delete <index>. \n"
                + "The minimum index is 1 and the maximum index is " + tasks.size());
    }

    /**
     * Prints a message notifying that a task has been added
     * @param tasks List of tasks
     * @param task Task to be added
     */
    public static void printTask(TaskList tasks, Task task) {
        System.out.println("Got it. I've added this lite.task: ");
        System.out.println(task);
        System.out.println("Now you have " + tasks.size() + " tasks in the tasks");
    }

    /**
     * Prints an error message when ToDo task does not meet input format
     */
    public static void toDoException() {
        System.out.println("Please give a name for the todo lite.task. \n +" +
                "The correct format is todo <description>");
    }

    /**
     * Prints an error message when Deadline task does not meet input format
     */
    public static void deadlineException() {
        System.out.println("Invalid input. \n" +
                "Please follow the format of: deadline <description> /by <date>");
    }

    /**
     * Prints an error message when Event task does not meet input format
     */
    public static void eventException() {
        System.out.println("Invalid input. \n" +
                "Please follow the format: event <description> /from <date> /to <date>");
    }

    /**
     * Prints out a message notifying that a task has been unmarked
     * @param task Task being unmarked
     */
    public static void printUnmark(Task task) {
        System.out.println(task.unmark());
    }

    /**
     * Prints  an error message when unmark does not meet the input format
     * @param tasks List of tasks
     */
    public static void unmarkException(TaskList tasks) {
        System.out.println("Please input a valid index. \n"
                + "The correct format is unmark <index>. \n"
                + "The minimum index is 1 and the maximum index is " + tasks.size());
    }

    /**
     * Prints out a message notifying that a task has been marked
     * @param task Task being marked
     */
    public static void printMark(Task task) {
        System.out.println(task.mark());
    }

    /**
     * Prints  an error message when mark does not meet the input format
     * @param tasks List of tasks
     */
    public static void markException(TaskList tasks) {
        System.out.println("Please input a valid index. \n"
                + "The correct format is mark <index>. \n"
                + "The minimum index is 1 and the maximum index is " + tasks.size());
    }

    public static void successfulFind(TaskList tasks) {
        System.out.println("Here are the matching tasks in your list:");
        tasks.taskString();
    }

    public static void unsuccessfulFind() {
        System.out.println("There are no tasks that correspond with your input");
    }
}
