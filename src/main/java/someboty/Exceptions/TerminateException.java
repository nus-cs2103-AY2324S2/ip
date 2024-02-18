package someboty.exceptions;

/**
 * A special case Exception that simply stops all pending actions that
 * skips straight to closing off the bot.
 */
public class TerminateException extends RuntimeException{
    public TerminateException(String errorMessage) {
        super(errorMessage);
    }
}
