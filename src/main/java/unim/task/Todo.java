package unim.task;

/**
 * Todo - Represents a todo task, a subclass of Task.
 */
public class Todo extends Task {

    /**
     * Creates a Todo task with the specified description.
     *
     * @param description The description of the todo task.
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return super.getStatusIcon() + "[T] " + getDescription();
    }
}

