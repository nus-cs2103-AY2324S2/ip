package tasks;

/**
 * represents a task with desciption only.
 */
public class Todo extends Task {
    /**
     * Constructs a Todo object.
     *
     * @param description The task description.
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
