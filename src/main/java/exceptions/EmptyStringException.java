package exceptions;

/**
 * Custom exception class representing the scenario where a search query is empty.
 * Extends the DukeExceptions class.
 */
public class EmptyStringException extends DukeExceptions {

    /**
     * The message to be displayed when EmptyStringException is thrown.
     */
    private String msg = "Search query cannot be empty!";

    public EmptyStringException() {
        super();
    }

    public String output() {
        return String.format("%s%s\n", super.output(), this.msg);
    }

}

