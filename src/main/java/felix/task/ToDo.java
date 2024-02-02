package felix.task;

public class ToDo extends Task {
    /**
     * Constructor for ToDo class.
     * @param description Description of task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns the String representation of the ToDo instance to be written to file.
     */
    @Override
    public String toFileString() {
        return String.format("T | %s | %s",this.getStatusIcon(), this.getDescription());
    }

    @Override
    public String toString() {
        return String.format("[T]%s",super.toString());
    }
}
