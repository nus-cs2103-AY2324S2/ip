package duke;

/**
 * Represents exceptions specific to the Duke chatbot application.
 * This class is used to encapsulate errors that occur during the application's operation, providing a clear message
 * regarding what went wrong. It extends the Exception class, allowing the Duke application to handle errors
 * gracefully and inform the user accordingly.
 */
public class ChatbotException extends Exception {
    /**
     * Constructs a new Exception with the specified detail message.
     * The message provides a clear description of the exception, intended to be shown directly to the user or logged
     * for debugging purposes.
     *
     * @param message The detail message. The detail message is saved for later retrieval
     *               by the {@link #getMessage()} method.
     */

    public ChatbotException(String message) {
        super(message);
    }
}
