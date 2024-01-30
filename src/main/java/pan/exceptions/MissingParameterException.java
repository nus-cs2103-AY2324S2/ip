package pan.exceptions;
import java.lang.Exception;

public class MissingParameterException extends Exception {
    /**
     * Creates a MissingParameterException instance.
     * 
     * @param message error message of the given exception
     */
    public MissingParameterException(String message) {
        super("Missing Parameter Exception: " + message);
    }
}