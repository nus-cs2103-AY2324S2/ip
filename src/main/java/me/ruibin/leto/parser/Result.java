package me.ruibin.leto.parser;

/**
 * Class for storing status of commands and output to be printed so far.
 * Contains the latestException if any.
 */
public class Result {
    private ResultTypes type;
    private StringBuilder messageBuilder;
    private Exception latestException;

    private Result(ResultTypes type, String message, Exception e) {
        this.type = type;
        this.messageBuilder = new StringBuilder(message);
        this.latestException = e;
    }

    public static Result makeResultOk(String message) {
        return new Result(ResultTypes.OK, message, null);
    }

    public static Result makeResultError(String message, Exception e) {
        return new Result(ResultTypes.ERROR, message, e);
    }

    public static Result makeResultExit(String message) {
        return new Result(ResultTypes.EXIT, message, null);
    }

    /**
     * Return current StringBuilder with appended messages.
     *
     * @param message new message to be appended.
     * @return Appended message builder.
     */
    public StringBuilder updateMessage(String message) {
        messageBuilder.append(message);
        return messageBuilder;
    }

    /**
     * Ideally only have one exception, but sometimes there is no need to carry
     * everything up. Changes the exception carried to this.
     *
     * @param e Lastest exception while calling the function.
     */
    public void updateException(Exception e) {
        latestException = e;
    }

    /**
     * Changes the result type.
     *
     * @param newType new Result Type.
     */
    public void updateType(ResultTypes newType) {
        type = newType;
    }

    /**
     * Return type of result.
     *
     * @return The type part of <code>ResultTypes</code>.
     */
    public ResultTypes getType() {
        return type;
    }

    /**
     * Update result to become of type Error and update message and
     * exception stored.
     *
     * @param message New message to append.
     * @param e Latest Exception to store.
     */
    public void updateWithException(String message, Exception e) {
        type = ResultTypes.ERROR;
        messageBuilder.append(message);
        latestException = e;
    }

    public String getMessage() {
        return messageBuilder.toString();
    }

    public Exception getLatestException() {
        return latestException;
    }
}
