package duke;

/**
 * Constructs exceptions specific to the Duke bot.
 */
public class DukeBotException extends Exception {
    // unknown command
    //  todo - no description
    // event - no description, no from, no to
    // deadline - no description, no by

    public DukeBotException(String message) {
        super(message);
    }

    /**
     * Exception thrown when an unknown command is given to the bot.
     */
    public static class UnknownCommandException extends DukeBotException {
        public UnknownCommandException() {
            super("Hmmmm...I don't understand");
        }
    }

    /**
     * Exception thrown when the arguments given to the find command are invalid.
     */
    public static class FindParamsException extends DukeBotException {
        public FindParamsException() {
            super("Oops! You need to enter a string for the find command!");
        }
    }

    /**
     * Exception thrown when the arguments given to the mark command are invalid.
     */
    public static class MarkParamsException extends DukeBotException {
        public MarkParamsException() {
            super("Oops! You need to enter an integer for the unmark or mark command!");
        }
    }

    /**
     * Exception thrown when the arguments given to the delete command are invalid.
     */
    public static class DeleteParamsException extends DukeBotException {
        public DeleteParamsException() {
            super("Oops! You need to enter an integer for the delete command!");
        }
    }

    /**
     * Exception thrown when the arguments given to the todo command are invalid or missing.
     */
    public static class TodoDescriptionMissingException extends DukeBotException {
        public TodoDescriptionMissingException() {
            super("Oops! Description for a todo task cannot be empty");
        }
    }

    /**
     * Exception thrown when the arguments given to the event command are invalid or missing.
     */
    public static class EventDetailsMissingException extends DukeBotException {
        public EventDetailsMissingException() {
            super("An event task must have a description and a duration ('from' and 'to')!");
        }
    }

    /**
     * Exception thrown when the arguments given to the deadline command are invalid or missing.
     */
    public static class DeadlineDetailsMissingException extends DukeBotException {
        public DeadlineDetailsMissingException() {
            super("A deadline task must have a description and a deadline ('by') !");
        }
    }

    /**
     * Exception thrown when a task is not found based on the given index.
     */
    public static class TaskNotFoundException extends DukeBotException {
        public TaskNotFoundException() {
            super("Oops! Could not find the task!");
        }
    }
}
