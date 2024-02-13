package chatbot;

/**
 * Represents a task in the chatbot.
 */
public class AlfredException extends Exception{
    public AlfredException(String message) {
        super(message);
    }
    @Override
    public String toString() {
        return this.getMessage();
    }
}
