package checkbot.exception;

import checkbot.task.Task;

/**
 * Represents an exception when a duplicate task is added into the task list.
 */
public class DuplicateTaskException extends CheckbotException {
    public DuplicateTaskException(Task task) {
        super("You already have this task in your task list:\n" + task);
    }
}
