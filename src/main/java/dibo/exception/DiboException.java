package dibo.exception;

/**
 * Class to handle exception related to the Dibo chatbot.
 */
public class DiboException extends Exception {

    /**
     * Constructor for the DiboException
     * @param errorMessage Error message to be printed for the user.
     */
    public DiboException(String errorMessage) {
        super(errorMessage);
    }

}
