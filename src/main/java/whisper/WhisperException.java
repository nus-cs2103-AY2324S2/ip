package whisper;

/**
 * The WhisperException class represents custom exceptions specific to the Whisper application.
 * It extends the Java Exception class and includes various static methods to create instances of specific exceptions.
 */
public class WhisperException extends Exception {

    /**
     * Constructs a new WhisperException with the specified error message.
     *
     * @param err The error message associated with the exception.
     */
    public WhisperException(String err) {
        super(err);
    }

    /**
     * Creates a WhisperException for an unknown command.
     *
     * @return A WhisperException indicating an invalid command.
     */
    public static WhisperException unknownCommand() {
        return new WhisperException("\nInvalid Command, please try again.\nWe only support commands such as:" +
                "\n- todo [description]\n- event [description] /from [start] /to [end]" +
                "\n- deadline [description] /by[time]\n- mark [taskID]\n- unmark [taskID]\n- delete [taskID]\n");
    }

    /**
     * Creates a WhisperException for when the task list is full.
     *
     * @return A WhisperException indicating that the task list is full.
     */
    public static WhisperException taskFull() {
        return new WhisperException("\nSorry, list is full. Can't add anymore.\n");
    }

    /**
     * Creates a WhisperException for an invalid event format.
     *
     * @return A WhisperException indicating an invalid event format.
     */
    public static WhisperException invalidEvent() {
        return new WhisperException("\nInvalid format. Please enter again (event [description] /from [start] /to [end])"
                 + ".\n");
    }

    /**
     * Creates a WhisperException for an invalid todo format.
     *
     * @return A WhisperException indicating an invalid todo format.
     */
    public static WhisperException invalidTodo() {
        return new WhisperException("\nInvalid format. Please enter again (todo [description]).\n");
    }

    /**
     * Creates a WhisperException for an invalid deadline format.
     *
     * @return A WhisperException indicating an invalid deadline format.
     */
    public static WhisperException invalidDeadline() {
        return new WhisperException("\nInvalid format. Please enter again (deadline [description] /by[time]).\n");
    }

    /**
     * Creates a WhisperException for an invalid task ID.
     *
     * @return A WhisperException indicating an invalid task ID.
     */
    public static WhisperException invalidTaskID() {
        return new WhisperException("\nInvalid task number. Please try again!");
    }

    /**
     * Creates a WhisperException for handling corrupted file content.
     *
     * @return A WhisperException indicating an error parsing tasks from a file.
     */
    public static WhisperException invalidFileFormat() {
        return new WhisperException("\nError parsing task from file.");
    }
}