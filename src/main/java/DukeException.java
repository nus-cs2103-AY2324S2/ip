public class DukeException extends Exception {
    // unknown command
    //  todo - no description
    // event - no description, no from, no to
    // deadline - no description, no by

    public DukeException(String message) {
        super(message);
    }

    // Custom exception for unknown command
    public static class UnknownCommandException extends DukeException {
        public UnknownCommandException() {
            super("Unknown command. Please enter a valid command :3");
        }
    }

    // Custom exception for unknown command
    public static class MarkParamsException extends DukeException {
        public MarkParamsException() {
            super("An integer argument is expected for a mark or unmark command :3");
        }
    }

    public static class DeleteParamsException extends DukeException {
        public DeleteParamsException() {
            super("An integer argument is expected for a delete command.");
        }
    }

    // Custom exception for "todo" command without a description
    public static class TodoDescriptionMissingException extends DukeException {
        public TodoDescriptionMissingException() {
            super("The description for a todo task cannot be empty :3");
        }
    }

    // Custom exception for "event" command without a description, from, or to
    public static class EventDetailsMissingException extends DukeException {
        public EventDetailsMissingException() {
            super("An event task must have a description, a 'from' time, and a 'to' time :3");
        }
    }

    // Custom exception for "deadline" command without a description or by
    public static class DeadlineDetailsMissingException extends DukeException {
        public DeadlineDetailsMissingException() {
            super("A deadline task must have a description and a 'by' time :3");
        }
    }

    // Custom exception for getTask where index is wrong, or the entry is not of type T, E, D
    public static class GetTaskException extends DukeException {
        public GetTaskException() {
            super("An error occurred when getting task, reinput your index!");
        }
    }
}
