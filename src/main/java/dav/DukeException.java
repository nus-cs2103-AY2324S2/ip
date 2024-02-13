package dav;
/**
 * Custom exception class for Dav application.
 * Represents exceptions specific to the Duke application.
 */
class DukeException extends Exception {
    /**
     * Constructs a new DukeException with the specified detail message.
     * @param message The detail message (which is saved for later retrieval by the getMessage() method).
     */
    public DukeException(String message) {
        super(message);
    }
}