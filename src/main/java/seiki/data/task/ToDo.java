package seiki.data.task;

/**
 * Represents a todo task.
 */
public class ToDo extends Task {
    public ToDo(String taskTitle) {
        super(taskTitle);
    }

    public ToDo(String taskTitle, boolean isDone) {
        super(taskTitle, isDone);
    }


    @Override
    public String toString() {
        return "[T] " + super.toString();
    }

    @Override
    public String toFile() {
        return "T " + super.toFile();
    }
}
