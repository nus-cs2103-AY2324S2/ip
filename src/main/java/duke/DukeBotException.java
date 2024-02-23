package duke;

/**
 * Contains exceptions that will be handled by the chat bot
 */

public class DukeBotException extends Exception {

    public DukeBotException(String message) {
        super(message);
    }

    /**
     * Exception thrown when unknown command is given to the bot.
     */
    public static class UnknownException extends DukeBotException {
        public UnknownException() {
            super("Sorry! I dont understand, try provided commands!");
        }
    }

    /**
     * Exception thrown when a task is not found based on given integer
     */
    public static class TaskException extends DukeBotException {
        public TaskException() {
            super("Oops! Could not find the task!");
        }
    }

    /**
     * Exception thrown when there are invalid or no arguments given to the todo command.
     */
    public static class TodoException extends DukeBotException {
        public TodoException() {
            super("Oops! Description for a todo task cannot be empty");
        }
    }

    /**
     * Exception thrown when there are invalid or no arguments given to the event command.
     */
    public static class EventException extends DukeBotException {
        public EventException() {
            super("Oops! An event must have a description and a duration!");
        }
    }

    /**
     * Exception thrown when there are invalid or no arguments given to the deadline command.
     */
    public static class DeadlineException extends DukeBotException {
        public DeadlineException() {
            super("Oops! A deadline must have a description and deadline!");
        }
    }

    /**
     * Exception thrown when invalid arguments are given to the delete command.
     */
    public static class DeleteException extends DukeBotException {
        public DeleteException() {
            super("Oops! You need to enter an integer for deleting!");
        }
    }

    /**
     * Exception thrown when invalid arguments are given to mark command.
     */
    public static class MarkException extends DukeBotException {
        public MarkException() {
            super("Oops! You need to enter an integer!");
        }
    }

    /**
     * Exception thrown when invalid arguments are given to find command.
     */
    public static class FindException extends DukeBotException {
        public FindException() {
            super("Oops! You need to enter a string to find!");
        }
    }
}
