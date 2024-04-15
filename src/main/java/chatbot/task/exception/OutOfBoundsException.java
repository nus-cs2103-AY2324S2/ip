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
    private final int outOfBoundsIndex;

    /** Stores the reason. */
    private final String outOfBoundsReason;
    /**
     * Constructor for this exception that is an index of a {@link TaskList} being out of range.
     */
    public OutOfBoundsException(int outOfBoundsIndex, String outOfBoundsReason) {
        this.outOfBoundsIndex = outOfBoundsIndex;
        this.outOfBoundsReason = outOfBoundsReason;
    }

    @Override
    public String getMessage() {
        return String.format("`%s` is out of bounds. %s", outOfBoundsIndex, outOfBoundsReason);
    }
}
