package missminutes;

/**
 * Wrapper class for exceptions specific to MissMinutes chatbot
 */
public class MissMinutesException extends Exception {

    /**
     * Constructs MissMinutesException with the given errorMsg
     *
     * @param msg The message of the exception
     */
    public MissMinutesException(String msg) {
        super(msg);
    }

    /**
     * Constructs the MissMinutesException with given errorMsg and cause. Wraps another exception.
     *
     * @param msg The message of the exception
     * @param cause The cause of the exception
     */
    public MissMinutesException(String msg, Throwable cause) {
        super(msg, cause);
    }
}