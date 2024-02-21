package julia;
// DukeExceptions.java

/**
 * Custom exception class for Duke.
 * Represents exceptions specific to the Duke program.
 */

public class DukeException extends Exception {

    /**
     * Constructs a DukeException with the specified detail message.
     *
     * @param message The detail message (which is saved for later retrieval
     *                by the {@link #getMessage()} method).
     */
    public DukeException(String message) {
        super(message);
    }

    /**
     * Exception class for an empty description in a Todo task.
     * This exception is thrown when attempting to create a Todo task with an empty description.
     */
    public static class EmptyTodoDescriptionException extends DukeException {

        /**
         * Constructs an EmptyTodoDescriptionException with a default message.
         * The default message is "OOPS!!! The description of a todo cannot be empty."
         */
        public EmptyTodoDescriptionException() {
            super("OOPS!!! The description of a todo cannot be empty.");
        }
    }

    /**
     * Exception class for an unknown command.
     * This exception is thrown when an unknown command is encountered in the Duke program.
     */
    public static class UnknownCommandException extends DukeException {

        /**
         * Constructs an UnknownCommandException with a default message.
         * The default message is "OOPS!!! I'm sorry, but I don't know what that means :-(".
         */
        public UnknownCommandException() {
            super("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }



}


