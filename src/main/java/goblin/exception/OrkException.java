package goblin.exception;

public class OrkException extends Exception{
    protected String message;

    /**
     * create a new OrkException object
     * @param message the message
     */
    public OrkException(String message) {
        this.message = message;
    }

    /**
     * a getter for the message
     * @return the message
     */
    public String getMessage() {
        return message;
    }
}
