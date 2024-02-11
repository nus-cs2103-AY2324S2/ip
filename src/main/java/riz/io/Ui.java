package riz.io;

/**
 * A UI class mainly used to display greeting messages and any messages that might pop up to the user.
 * Also displays the any error messages.
 */
public class Ui {

    public Ui() {

    }

    /**
     * Prints the greeting message.
     */
    public static void printGreetings() {
        System.out.println("Hello... I'm Riz...\n"
                + "What can I help you with today?\n");
    }

    /**
     * Prints the goodbye message.
     */
    public static void printGoodbye() {
        System.out.println("Bye... Hope to see you again...\n");
    }

    /**
     * Prompts the user if he/she is sure to clear all tasks.
     */
    public static void printClearConfirmation() {
        System.out.println("Are you sure you want to delete all tasks? y/n");
    }

    /**
     * Prints the message that the TaskList is now empty.
     */
    public static void printAllCleared() {
        System.out.println("All tasks have been cleared...");
    }

    /**
     * prints the error when the mark command is input wrongly.
     */
    public static void markError() {
        RizException e = new RizException("Oh you sure you completed the task?...");
        System.out.println(e.getMessage());
    }

    /**
     * prints the error when the unmark command is input wrongly.
     */
    public static void unmarkError() {
        RizException e = new RizException("Seems like there's more to be done...");
        System.out.println(e.getMessage());
    }

    /**
     * prints the error when the delete command is input wrongly.
     */
    public static void deleteError() {
        RizException e = new RizException("Oh you sure you wanna delete the task?...");
        System.out.println(e.getMessage());
    }

    /**
     * prints the error when the ToDo command is input wrongly.
     */
    public static void todoError() {
        RizException e = new RizException("Indecisive aren't we...");
        System.out.println(e.getMessage());
    }

    /**
     * prints the error when the deadline command is input wrongly.
     */
    public static void deadlineError() {
        RizException e = new RizException("seems like the deadline isn't so soon after all...");
        System.out.println(e.getMessage());
    }

    /**
     * prints the error when the event command is input wrongly.
     */
    public static void eventError() {
        RizException e = new RizException("What? Did the event get cancelled...");
        System.out.println(e.getMessage());
    }

    /**
     * prints the error when the find command is input wrongly.
     */
    public static void findError() {
        RizException e = new RizException("What are you even looking for?...");
        System.out.println(e.getMessage());
    }

    /**
     * prints the error when the command is not recognised.
     */
    public static void yapError() {
        RizException e = new RizException("Are you speaking Yapanese?...");
        System.out.println(e.getMessage());
    }
}
