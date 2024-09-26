package seedu.banter.errors;

import seedu.banter.tasks.TaskList;
import seedu.banter.ui.Ui;


/**
 * Represents an error that occurs when the user uses an invalid task number.
 */
public class InvalidTaskNumberUsageError extends InvalidBanterUsageError {
    /**
     * Constructs a new InvalidTaskNumberUsageError object.
     * @param list Task list.
     */
    public InvalidTaskNumberUsageError(TaskList list) {
        super(Errors.INVALID_TASK_NUMBER, Ui.MARK_USAGE + "\n" + list);
    }
}
