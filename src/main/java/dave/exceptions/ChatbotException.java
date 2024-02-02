package dave.exceptions;

public class ChatbotException extends Exception {

    /**
     * Creates new ChatbotException.
     * ChatbotException are those that occurred from interactions with the chatbot Dave.
     * 
     * @param msg The message to show to the user to help them interact with Dave.
     */
    public ChatbotException(String msg) {
        super(msg);
    }
}
