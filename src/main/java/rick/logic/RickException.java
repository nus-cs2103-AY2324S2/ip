package rick.logic;

/**
 * Exeptions for Rick application.
 */

public class RickException extends Exception {
    private String message;

    /**
     * Creates a new RickException instance with specified error message.
     * @param message the error message.
     */
    public RickException(String message) {
        this.message = message;
    }

    /**
     * Returns the error message.
     * @return the error message.
     */
    public String getMessage() {
        return message;
    }
}
