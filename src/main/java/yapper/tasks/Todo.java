package yapper.tasks;

/**
 * The Todo class represents a task of type Todo.
 * It extends the Task class and includes additional methods specific to Todo tasks.
 */
public class Todo extends Task {
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String getType() {
        return "T";
    }
    @Override
    public String toFileString() {
        assert getDescription() != null : "Task description should not be null";
        return "T | " + (isDone ? "1" : "0") + " | "
                + getDescription();
    }
}
