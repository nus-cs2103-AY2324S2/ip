package exceptions;

/**
 * Represents an exception specific to the chatbot application.
 */
public class ChatBotException extends Exception {

    /**
     * Constructs a ChatBotException with the specified error message.
     *
     * @param errorMessage The error message describing the exception.
     */
    public ChatBotException(String errorMessage) {
        super("____________________________________________________________" + "\n"
                + "\t" + errorMessage + "\n" + "____________________________________________________________");
    }
}
