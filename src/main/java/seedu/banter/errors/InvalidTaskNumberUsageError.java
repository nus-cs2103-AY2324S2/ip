package seedu.banter.errors;

import seedu.banter.tasks.TaskList;


/**
 * Represents an error that occurs when the user uses an invalid task number.
 */
public class InvalidTaskNumberUsageError extends InvalidBanterUsageError {
    /**
     * Constructs a new InvalidTaskNumberUsageError object.
     * @param error Error message.
     * @param usage Usage message.
     * @param list Task list.
     */
    public InvalidTaskNumberUsageError(String error, String usage, TaskList list) {
        super(error, usage + "\n" + list);
    }
}
