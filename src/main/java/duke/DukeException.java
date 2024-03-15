//package duke;
//
///**
// * DukeException is an exception class for handling exceptions.
// * It extends the general Exception class.
// */
//public class DukeException extends Exception {
//    /**
//    * Constructs a DukeException with the specified error message.
//    *
//    * @param message The error message associated with the exception.
//    */
//    public DukeException(String message) {
//        super(message);
//    }
//}
package duke;

/**
 * DukeException is an exception class for handling exceptions in the Duke program.
 * It extends the general Exception class.
 */
public class DukeException extends Exception {

    private String errorMessage;

    /**
     * Constructs a DukeException with the specified error message.
     *
     * @param errorMessage The error message associated with the exception.
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }

    /**
     * Get the error message associated with this exception.
     *
     * @return The error message.
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * Set a new error message for this exception.
     *
     * @param errorMessage The new error message to set.
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return "DukeException: " + errorMessage;
    }
}


