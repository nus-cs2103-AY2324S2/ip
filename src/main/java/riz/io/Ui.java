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
        RizException e = new RizException("Oh you sure you completed the task?...");
        return e.getMessage();
    }

    /**
     * prints the error when the unmark command is input wrongly.
     */
    public static String unmarkError() {
        RizException e = new RizException("Seems like there's more to be done...");
        return e.getMessage();
    }

    /**
     * prints the error when the delete command is input wrongly.
     */
    public static String deleteError() {
        RizException e = new RizException("Oh you sure you wanna delete the task?...");
        return e.getMessage();
    }

    /**
     * prints the error when the ToDo command is input wrongly.
     */
    public static String todoError() {
        RizException e = new RizException("Indecisive aren't we...");
        return e.getMessage();
    }

    /**
     * prints the error when the deadline command is input wrongly.
     */
    public static String deadlineError() {
        RizException e = new RizException("seems like the deadline isn't so soon after all...");
        return e.getMessage();
    }

    /**
     * prints the error when the event command is input wrongly.
     */
    public static String eventError() {
        RizException e = new RizException("What? Did the event get cancelled...");
        return e.getMessage();
    }

    /**
     * prints the error when the find command is input wrongly.
     */
    public static String findError() {
        RizException e = new RizException("What are you even looking for?...");
        return e.getMessage();
    }

    /**
     * prints the error when the command is not recognised.
     */
    public static String yapError() {
        RizException e = new RizException("Are you speaking Yapanese?...");
        return e.getMessage();
    }
}