package theadvisor;

/**
 * Custom exception class for handling exceptions specific to TheAdvisor application.
 */
public class TheAdvisorException extends Exception {

    /**
     * Constructs a new TheAdvisorException with the specified detail message.
     *
     * @param message the detail message (which is saved for later retrieval by the getMessage() method).
     */
    public TheAdvisorException(String message) {
        super(message);
    }
}
