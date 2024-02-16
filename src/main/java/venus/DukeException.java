package venus;

/**
 * This class is dukeException which throws exceptions related to the duke project.
 *
 * @author peterXGD
 * @since 2024-02-05
 */
public class DukeException extends Exception {
    /**
     * An exception that is caused by problems related to project Duke.
     */
    public DukeException() {
        super("This is a duke exception of unknown cause.");
    }

    /**
     * An exception that is caused by problems related to project Duke with custom exception.
     */
    public DukeException(String s) {
        super(s);
    }
}
