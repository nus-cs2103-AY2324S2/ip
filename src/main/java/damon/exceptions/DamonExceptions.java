package damon.exceptions;

/**
 * Represents parent Exception specific to Damon.
 */
public class DamonExceptions extends Exception{

    protected String message;

    /**
     * Constructs a new DamonExceptions object.
     */
    public DamonExceptions() {
        this.message = " ";
    }

    /**
     * Returns message of DamonExceptions.
     *
     * @return Error message.
     */
    public String getMessage() {
        return this.message;
    }
}