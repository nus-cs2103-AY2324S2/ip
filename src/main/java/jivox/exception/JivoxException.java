package jivox.exception;

/**
 * JivoxException is a special exception
 * particularly for the bot Jivox and to report
 * invalid scenerios that bot can't handle
 */
public class JivoxException extends Exception {
    public JivoxException(String message) {
        super(message);
    }
}
