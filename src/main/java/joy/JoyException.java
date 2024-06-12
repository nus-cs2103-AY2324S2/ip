package joy;
// DukeExceptions.java

/**
 * Custom exception class for Duke.
 * Represents exceptions specific to the Duke program.
 */

public class JoyException extends Exception {

    /**
     * Constructs a DukeException with the specified detail message.
     *
     * @param message The detail message (which is saved for later retrieval
     *                by the {@link #getMessage()} method).
     */
    public JoyException(String message) {
        super(message);
    }

    /**
     * Exception class for wrong format of deadline tasks.
     * This exception is thrown when attempting to create
     * a Deadline task with an empty description or by time.
     */
    public static class DeadlineException extends JoyException {

        /**
         * Constructs a DeadlineException with the specified detail message.
         *
         * @param message The detail message (which is saved for later retrieval
         *                by the {@link #getMessage()} method).
         */
        public DeadlineException(String message) {
            super(message + "\n"
                    +
                    "Note that deadline tasks must be of format: \n deadline [description] /by yyyy-mm-dd");
        }
    }

    /**
     * Exception class for wrong format of event tasks.
     * This exception is thrown when attempting to create
     * an Event task with an empty description, from time, or to time.
     */
    public static class EventException extends JoyException {

        /**
         * Constructs a EventException with the specified detail message.
         *
         * @param message The detail message (which is saved for later retrieval
         *                by the {@link #getMessage()} method).
         */
        public EventException(String message) {
            super(message + "\n"
                    +
                    "Note that event tasks must be of format: \n event [description] /from [time] /to [time]");
        }
    }


    /**
     * Exception class for an empty description in a Todo task.
     * This exception is thrown when attempting to create a Todo task with an empty description.
     */
    public static class EmptyTodoDescriptionException extends JoyException {

        /**
         * Constructs an EmptyTodoDescriptionException with a default message.
         * The default message is "OOPS!!! The description of a todo cannot be empty."
         */
        public EmptyTodoDescriptionException() {
            super("OOPS!!! The description of a todo cannot be empty."
                    +
                    "\n Note that todo tasks must be in format: \n todo [description]");
        }
    }

    /**
     * Exception class for an unknown command.
     * This exception is thrown when an unknown command is encountered in the Duke program.
     */
    public static class UnknownCommandException extends JoyException {

        /**
         * Constructs an UnknownCommandException with a default message.
         * The default message is "OOPS!!! I'm sorry, but I don't know what that means :-(".
         */
        public UnknownCommandException() {
            super("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }



}


