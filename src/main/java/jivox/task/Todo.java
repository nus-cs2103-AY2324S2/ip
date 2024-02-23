package jivox.task;

/**
 * Todo represents a simple todo task.
 * It extends Task.
 */
public class Todo extends Task {

    /**
     * Creates a new Todo task with the given description.
     *
     * @param content The task description.
     */
    public Todo(String content) {
        super(content);
    }

    /**
     * Gets the type identifier for Todo, which is "T".
     *
     * @return The identifier "T".
     */
    public String getType() {
        return "T";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString()
                + (this.getTag().isEmpty() ? "" : " #" + this.getTag());
    }

    /**
     * Gets the save format string for this Todo task.
     *
     * @return Save format string.
     */
    @Override
    public String saveFormat() {
        return this.getType() + " | "
                + (this.getStatus() ? "1" : "0") + " | " + this.getDescription()
                + " tag " + (this.getTag().isEmpty() ? "None" : this.getTag());
    }
}
