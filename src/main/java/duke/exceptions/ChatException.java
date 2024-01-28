package duke.exceptions;

/**
 * The general exception our chatbot uses.
 */
public class ChatException extends RuntimeException {
    public ChatException(String msg) {
        super(msg);
    }

    @Override
    public String getMessage() {
        String val = super.getMessage();
        return String.format("OOPS!!! " + val);
    }
}
