package megatron.data.exception;

/**
 * Exception class for errors related to Duke
 */
public class DukeException extends Exception {
    public DukeException(String errorDesc) {
        super(errorDesc);
    }
}
