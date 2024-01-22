import java.util.*;
import java.io.*;

/**
 * Custom exception that handles edge cases such as invalid command etc.
 * @author Koo Zhuo Hui
 */
public class DukeException extends RuntimeException {

    /**
     * Constructs the exception
     * @param message the message to be printed to user.
     */
    public DukeException(String message) {
        super(message);
    }
}
