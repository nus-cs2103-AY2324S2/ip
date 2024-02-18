package lite.util;

import lite.task.TaskList;

import java.io.IOException;

public class LiteException extends Exception{
    public LiteException(String error) {
        super(error);
    }
    /**
     * Outputs an error message when an invalid input is given
     */
    public static String invalidInput() {
        return "Invalid input .\n" +
                "Please begin your input with either one of these keywords: \n" +
                "todo, list, deadline, event, mark, unmark, delete, find, bye";

    }

    /**
     * Outputs an error message when a file fails to load
     */
    public static String loadFileException() {
        return "Unable to load data from local file\n" + "File may be corrupted";
    }

    /**
     * Outputs an error messagae when a file fails to save
     * @param e Error message corresponding to the error
     */
    public static String saveException(IOException e) {
        return "Failed to save to a local file \n" + e;
    }

    /**
     * Prints an error message when delete does not meet the input format
     * @param tasks List of tasks
     */
    public static String deleteException(TaskList tasks) {
        return "Please input a valid index. \n"
                + "The correct format is delete <index>. \n"
                + "The minimum index is 1 and the maximum index is " + tasks.size();
    }

    /**
     * Prints an error message when ToDo task does not meet input format
     */
    public static String toDoException() {
        return "Please give a name for the todo lite.task. \n +" +
                "The correct format is todo <description>";
    }

    /**
     * Prints an error message when Event task does not meet input format
     */
    public static String eventException() {
        return "Invalid input. \n" +
                "Please follow the format: event <description> /from <date> /to <date>";
    }

    /**
     * Prints an error message when Deadline task does not meet input format
     */
    public static String deadlineException() {
        return "Invalid input. \n" +
                "Please follow the format of: deadline <description> /by <date>";
    }

    /**
     * Prints  an error message when unmark does not meet the input format
     * @param tasks List of tasks
     */
    public static String unmarkException(TaskList tasks) {
        return "Please input a valid index. \n"
                + "The correct format is unmark <index>. \n"
                + "The minimum index is 1 and the maximum index is " + tasks.size();
    }

    /**
     * Prints  an error message when mark does not meet the input format
     * @param tasks List of tasks
     */
    public static String markException(TaskList tasks) {
        return "Please input a valid index. \n"
                + "The correct format is mark <index>. \n"
                + "The minimum index is 1 and the maximum index is " + tasks.size();
    }
}
