package duke.exceptions;

public class TaskNotFoundException extends DukeException {
    public TaskNotFoundException() {
        super("Task not found. Use list to view all tasks.");
    }
}
