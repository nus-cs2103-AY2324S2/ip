package felix.task;

/**
 * Class representing tasks with no provided time constraints
 */
public class ToDo extends Task {
    /**
     * Constructor for ToDo class.
     * @param description Description of task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns a new ToDo instance with updated description.
     * @param paramString String containing new description.
     */
    @Override
    public ToDo updateTask(String paramString) {
        return new ToDo(paramString);
    }

    /**
     * Returns the String representation of the ToDo instance to be written to file.
     */
    @Override
    public String toFileString() {
        return String.format("T | %s | %s", this.getStatusIcon(), this.getDescription());
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
