package task;

/**
 * Represents a todo type task.
 */
public class Todo extends Task{
    /**
     * Creates a Todo object with description.
     *
     * @param description Description of task.
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String writeTask() {
        return "T | " + super.writeTask();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
