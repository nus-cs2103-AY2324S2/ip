package duke;

/**
 * The CustomExceptions class provides a convenient class
 * from which all exceptions unique to the project can
 * extend from.
 */
public class CustomExceptions extends Exception {

    public CustomExceptions(String errorMessage) {
        super(errorMessage);
    }

    /**
     * InvalidTaskException is thrown when a command with a valid
     * identifier (e.g. list, event, deadline etc.) is not followed
     * by other required information, leading to error in parsing the
     * command.
     */
    public static class InvalidTaskException extends CustomExceptions {
        public InvalidTaskException(String errorMessage) {
            super(errorMessage);
        }
    }

    /**
     * UnrecognizedCommandException is thrown in 2 cases. First,
     * when there is no recognized command identifier in the given
     * command. Second, when a recognized command to modify the ItemList
     * using index access contains a negative index.
     */
    public static class UnrecognizedCommandException extends CustomExceptions {
        public UnrecognizedCommandException(String errorMessage) {
            super(errorMessage);
        }
    }

    /**
     * ToBeforeFromException is thrown when the /to command is detected
     * before the /from command. This can occur if the /from command is
     * after the /to command, and also when the /from command is missing.
     */
    public static class ToBeforeFromException extends CustomExceptions {
        public ToBeforeFromException(String errorMessage) {
            super(errorMessage);
        }
    }

    /**
     * EventExceptionForFromTo is thrown when the /to and /from keywords
     * result in the start and end DateTimes of an event to fail to parse.
     */
    public static class EventExceptionForFromTo extends CustomExceptions {
        public EventExceptionForFromTo(String errorMessage) {
            super(errorMessage);
        }
    }

    /**
     * NamelessTaskException is thrown when an Item name failed to parse.
     */
    public static class NamelessTaskException extends CustomExceptions {
        public NamelessTaskException(String errorMessage) {
            super(errorMessage);
        }
    }

    /**
     * NamelessTaskException is thrown when an index out of bounds is passed.
     */
    public static class NoSuchIndexException extends CustomExceptions {
        public NoSuchIndexException(String errorMessage) {
            super(errorMessage);
        }
    }

    /**
     * NamelessTaskException is thrown when the LocalDateTime parse() method
     * fails to parse a string intended to be read as a DateTime. This wrapper
     * exception allows it to be considered a CustomException.
     */
    public static class UnrecognizableDateException extends CustomExceptions {
        public UnrecognizableDateException(String errorMessage) {
            super(errorMessage);
        }
    }

    /**
     * DeadlineExceptionBy is thrown when the /by keyword
     * results in the doneBy DateTime of a deadline to fail to parse.
     */
    public static class DeadlineExceptionBy extends CustomExceptions {
        public DeadlineExceptionBy(String errorMessage) {
            super(errorMessage);
        }
    }

    /**
     * MarkException is thrown when there is no following information after
     * the commands: mark, unmark or delete are recognized and parsed.
     */
    public static class MarkException extends CustomExceptions {
        public MarkException(String errorMessage) {
            super(errorMessage);
        }
    }

    /**
     * FindException is thrown when no search string is given after the find
     * command is recognized.
     */
    public static class FindException extends CustomExceptions {
        public FindException(String errorMessage) {
            super(errorMessage);
        }
    }
}
