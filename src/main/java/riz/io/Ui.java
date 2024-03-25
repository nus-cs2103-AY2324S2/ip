package riz.io;

/**
 * A UI class mainly used to display greeting messages and any messages that might pop up to the user.
 * Also displays the any error messages.
 */
public class Ui {

    public Ui() {

    }

    /**
     * Prints the message that the TaskList is now empty.
     */
    public static String printAllCleared() {
        return "All tasks have been cleared...";
    }

    /**
     * prints the error when the mark command is input wrongly.
     */
    public static String markError() {
        return "Oh you sure you completed the task?...";
    }

    /**
     * prints the error when the unmark command is input wrongly.
     */
    public static String unmarkError() {
        return "Seems like there's more to be done...";
    }

    /**
     * prints the error when the delete command is input wrongly.
     */
    public static String deleteError() {
        return "Error in deleting the task...";
    }

    public static String todoError() {
        return "Please enter your todo again...";
    }
    /**
     * prints the error when the deadline command is input wrongly.
     */
    public static String deadlineError() {
        return "seems like the deadline isn't so soon after all...";
    }

    /**
     * prints the error when the event command is input wrongly.
     */
    public static String eventError() {
        return "What? Did the event get cancelled...";
    }

    /**
     * prints the error when the find command is input wrongly.
     */
    public static String findError() {
        return "What are you even looking for?...";
    }

    /**
     * prints the error when the command is not recognised.
     */
    public static String yapError() {
        return "Are you speaking Yapanese?...";
    }
}