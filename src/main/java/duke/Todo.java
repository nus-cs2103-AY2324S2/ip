package duke;
/**
 * Represents a todo task that can store the description and status of the task.
 */
public class Todo extends Task {
    /**
     * Initializes a Todo with the description and status of not done.
     *
     * @param description the description of the task
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String getTaskType() {
        return "T";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
