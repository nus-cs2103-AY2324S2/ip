package anna;

/**
 * The AnnaException class represents an exception specific to the Anna application.
 */
class AnnaException extends Exception {

    /**
     * Constructs a new AnnaException with the specified detail message.
     *
     * @param message the detail message (which is saved for later retrieval by the getMessage() method)
     */
    public AnnaException(String message) {
        super(message);
    }
}
