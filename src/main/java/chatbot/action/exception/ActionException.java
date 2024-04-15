package chatbot.action.exception;

import chatbot.action.Action;
import chatbot.exception.ChatBotException;

/**
 * This represents exceptions specific to {@link Action},
 * which are thrown when invalid input results in an {@link Action} being invalid,
 * and is unable to recover a valid state.
 *
 * @author Titus Chew
 */
public abstract class ActionException extends ChatBotException {

}
