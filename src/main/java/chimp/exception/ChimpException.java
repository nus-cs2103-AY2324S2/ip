package chimp.exception;
public abstract class ChimpException extends Exception{
    /**
     * Represents an exception specific to the Chimp application.
     * This exception is thrown when an error occurs during the execution of Chimp.
     */
    ChimpException(String message) {
        super(message);
    }
    /**
     * Returns a string representation of the object.
     *
     * @return a string representation of the object.
     */
    @Override
    public abstract String toString();
}
