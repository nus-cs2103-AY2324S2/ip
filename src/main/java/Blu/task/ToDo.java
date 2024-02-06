package blu.task;

/**
 * Represents a task with a title.
 * Inherits from the {@link Task} class.
 */
public class ToDo extends Task {
    private static final String TASK_TYPE = "T";

    /**
     * Constructs a new ToDo task with the specified title.
     *
     * @param title The title of the ToDo task.
     */
    public ToDo(String title) {
        super(title);
    }

    @Override
    public String toCsv() {
        return String.format("%s,%s,%s", TASK_TYPE, this.isCompleted() ? "T" : "F", this.getTitle());
    }

    @Override
    public String toString() {
        return String.format("[%s]%s", TASK_TYPE, super.toString());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ToDo)) {
            return false;
        }
        ToDo other = (ToDo) obj;
        return super.equals(other);
    }
}
