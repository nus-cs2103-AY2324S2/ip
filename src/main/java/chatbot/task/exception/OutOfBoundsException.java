package chatbot.task.exception;

import chatbot.exception.ChatBotException;
import chatbot.task.TaskList;

/**
 * This represents exceptions due to the index for a {@link TaskList} being out of range.
 *
 * @author Titus Chew
 */
public final class OutOfBoundsException extends ChatBotException {
    /** Stores the index. */
    private final int index;

    /** Stores the reason. */
    private final String reason;
    /**
     * Constructor for this exception that is an index of a {@link TaskList} being out of range.
     *
     * @param index the index
     * @param reason the reason
     */
    public OutOfBoundsException(int index, String reason) {
        this.index = index;
        this.reason = reason;
    }

    @Override
    public String getMessage() {
        return String.format("`%s` is out of bounds. %s", index, reason);
    }
}
