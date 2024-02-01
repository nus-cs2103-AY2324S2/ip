package harper.tasks;

public class ToDo extends Task {
    /**
     * Creates a task.ToDo task with description.
     *
     * @param description The description of the task.ToDo task.
     */
    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return "T" + super.toString();
    }
}
