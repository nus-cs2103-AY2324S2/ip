package tyler.task;

/**
 * Represents a Todo task.
 */
public class Todo extends Task {
    public Todo(String name) {
        super(name);
    }

    /**
     * Constructor of Todo Task.
     *
     * @param name   Name of Task.
     * @param isDone Status of Task.
     */
    public Todo(String name, boolean isDone) {
        super(name);
        this.isDone = isDone;
    }

    /**
     * This method is used by storage when the storage need to save file to local.
     *
     * @return String representation of this task to be saved to the local.
     */
    @Override
    public String saveToFileString() {
        return "T | " + super.saveToFileString();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
