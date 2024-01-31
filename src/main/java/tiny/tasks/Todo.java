package tiny.tasks;

public class Todo extends Task {

    /**
     * Initialize Todo.
     *
     * @param description Description of the task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Initialize Todo.
     *
     * @param description Description of the task.
     * @param isDone      Status of the task.
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toSave() {
        return "T" + super.toSave();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
