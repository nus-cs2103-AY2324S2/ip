package seedu.duke.exception;

import seedu.duke.task.Task;

/**
 * An exception when user entered totally same task
 */
public class DuplicateTaskException extends DukeException {
    /**
     * The constructor of the exception
     *
     * @param task the task that is being duplicated
     */
    public DuplicateTaskException(Task task) {
        super(String.format("Bro, the task: %s is already in the list, you may want to try a different name or date",
                task));
    }
}
