package bytetalker.exception;

/**
 * Represents all exceptions that can be caused during program execution.
 *
 * @author Junseo Kim
 * @version 0.1
 * @since 2024-01-28
 */
public class ByteTalkerException {

    /**
     * Exception when user input format for todo task is wrong.
     *
     * @author Junseo Kim
     * @version 0.1
     * @since 2024-01-28
     */
    public static class TodoUnsupportedFormatException extends Exception {
        public TodoUnsupportedFormatException(String errorMessage) {
            super(errorMessage);
        }
    }

    /**
     * Exception when user input format for deadline task is wrong.
     *
     * @author Junseo Kim
     * @version 0.1
     * @since 2024-01-28
     */
    public static class DeadlineWrongFormatException extends Exception {
        public DeadlineWrongFormatException() {
        }
    }

    /**
     * Exception when user input format for event task is wrong.
     * If the user inputs wrong command format to create an event task, this exception will be thrown.
     *
     * @author Junseo Kim
     * @version 0.1
     * @since 2024-01-28
     */
    public static class EventWrongFormatException extends  Exception {
        public EventWrongFormatException() {
        }
    }

    /**
     * Exception for unsupported task.
     * If the user inputs unsupported command format, this exception will be thrown.
     *
     * @author Junseo Kim
     * @version 0.1
     * @since 2024-01-28
     */
    public static class UnsupportedTaskException extends Exception {
        public UnsupportedTaskException(String errorMessage) {
            super(errorMessage);
        }
    }

    /**
     * Exception when user input format for DateTime is wrong.
     *
     * @author Junseo Kim
     * @version 0.1
     * @since 2024-01-28
     */
    public static class UnsupportedDateTimeException extends Exception {
        public UnsupportedDateTimeException(String errorMessage) {
            super(errorMessage);
        }
    }
}
