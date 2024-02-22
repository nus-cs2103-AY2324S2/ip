package chatbot.exception;

import chatbot.ChatBot;

/**
 * This represents exceptions thrown by a {@link ChatBot}.
 *
 * @author Titus Chew
 */
public abstract class ChatBotException extends Exception {
    /**
     * Gets the message of this exception, which can be printed to the console.
     *
     * @return the message
     */
    @Override
    public abstract String getMessage();
}
