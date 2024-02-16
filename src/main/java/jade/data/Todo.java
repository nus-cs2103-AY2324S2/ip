package jade.data;

/**
 * The <code>Todo</code> object represents a user todo task.
 */
public class Todo extends Task {
    /**
     * Class constructor specifying the task description.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Another Class constructor specifying the task description and completion status.
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
