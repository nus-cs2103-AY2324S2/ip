package lindi.task;

/**
 * Represents an Exception related to creation of ToDo task
 */
public class CreateToDoException extends CreateTaskException {
    public CreateToDoException(String s) {
        super(s);
    }
}
