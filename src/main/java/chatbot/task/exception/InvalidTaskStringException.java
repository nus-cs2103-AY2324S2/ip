package chatbot.task.exception;

import chatbot.exception.ChatBotException;

/**
 * This represents an exception from a {@link String} that doesn't match the regex pattern.
 *
 * @author Titus Chew
 */
public final class InvalidTaskStringException extends ChatBotException {
    @Override
    public String getMessage() {
        return "The regex pattern does not match the given string";
    }
}
