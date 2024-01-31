package duke;

/**
 * Constructs Exceptions that are specific to the Duke bot
 */
public class DukeException extends Exception {
    // unknown command
    //  todo - no description
    // event - no description, no from, no to
    // deadline - no description, no by

    public DukeException(String message) {
        super(message);
    }

    /**
     * An Exception for when an unknown command is given to the bot
     */
    public static class UnknownCommandException extends DukeException {
        public UnknownCommandException() {
            super("Unknown command. Please enter a valid command :3");
        }
    }

    /**
     * An Exception for when the arguments given to the find command are invalid
     */
    public static class FindParamsException extends DukeException {
        public FindParamsException() {
            super("A string keyword is expected for a find command :3");
        }
    }

    /**
     * An Exception for when the arguments given to the mark command are invalid
     */
    public static class MarkParamsException extends DukeException {
        public MarkParamsException() {
            super("An integer argument is expected for a mark or unmark command :3");
        }
    }

    /**
     * An Exception for when the arguments given to the delete command are invalid
     */
    public static class DeleteParamsException extends DukeException {
        public DeleteParamsException() {
            super("An integer argument is expected for a delete command.");
        }
    }

    /**
     * An Exception for when the arguments given to the todo command are invalid or missing
     */
    public static class TodoDescriptionMissingException extends DukeException {
        public TodoDescriptionMissingException() {
            super("The description for a todo task cannot be empty :3");
        }
    }

    /**
     * An Exception for when the arguments given to the event command are invalid or missing
     */
    public static class EventDetailsMissingException extends DukeException {
        public EventDetailsMissingException() {
            super("An event task must have a description, a 'from' time, and a 'to' time :3");
        }
    }

    /**
     * An Exception for when the arguments given to the deadline command are invalid or missing
     */
    public static class DeadlineDetailsMissingException extends DukeException {
        public DeadlineDetailsMissingException() {
            super("A deadline task must have a description and a 'by' time :3");
        }
    }

    /**
     * An Exception for getTask where index is wrong, or the entry is not of type T, E, D
     */
    public static class TaskNotFoundException extends DukeException {
        public TaskNotFoundException() {
            super("Could not find task!");
        }
    }
}
