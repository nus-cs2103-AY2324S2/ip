package remi.utils;

/**
 * Local error class for the Remi app.
 */
public class RemiError extends Exception {
    /**
     * Basic constructor for the app's error.
     *
     * @param errorMessage the error message to be sent
     */
    public RemiError(String errorMessage) {
        super(errorMessage);
    }
}
