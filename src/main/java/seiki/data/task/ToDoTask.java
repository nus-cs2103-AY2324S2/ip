package seiki.data.task;

/**
 * Represents a todo task.
 */
public class ToDoTask extends Task {
    public ToDoTask(String taskTitle) {
        super(taskTitle);
    }

    public ToDoTask(String taskTitle, boolean isDone) {
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
