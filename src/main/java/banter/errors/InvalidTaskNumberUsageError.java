package banter.errors;

import banter.tasks.TaskList;

public class InvalidTaskNumberUsageError extends banter.errors.InvalidBanterUsageError {
    public InvalidTaskNumberUsageError(String error, String usage, TaskList list) {
        super(error, usage + "\n" + list);
    }
}
