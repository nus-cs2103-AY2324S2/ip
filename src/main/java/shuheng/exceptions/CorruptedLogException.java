package shuheng.exceptions;

/**
 * This class represents the error thrown with a corrupted log.
 */
public class CorruptedLogException extends DukeException {
    public CorruptedLogException(String msg) {
        super("Your log is corrupted please start a new one!\n" + msg);
    }
}
