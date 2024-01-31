package Duke.DukeException;

/**
 * This class is the exception class that is being thrown throughout the program.
 * @author Tang Hao Liang
 */
public class DukeException extends Exception {
    protected String error;

    /**
     * Constructor that takes in the error message to be shown.
     *
     * @param error
     */
    public DukeException(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return error;
    }
}
