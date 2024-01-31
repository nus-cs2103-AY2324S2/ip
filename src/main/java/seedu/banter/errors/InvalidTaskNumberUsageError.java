package seedu.banter.errors;

import seedu.banter.tasks.TaskList;

public class InvalidTaskNumberUsageError extends InvalidBanterUsageError {
    public InvalidTaskNumberUsageError(String error, String usage, TaskList list) {
        super(error, usage + "\n" + list);
    }
}
