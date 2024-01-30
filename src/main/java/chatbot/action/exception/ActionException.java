package chatbot.action.exception;

import chatbot.action.Action;

/**
 * ActionException represents exceptions specific to {@link Action},
 * which are thrown when invalid input results in an action being invalid,
 * and is unable to recover a valid state.
 *
 * @author Titus Chew
 */
public abstract class ActionException extends Exception {

}
