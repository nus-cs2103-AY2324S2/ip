package task;

/**
 * Represents a todo task.
 * Inherits from the Task class.
 */
public class Todo extends Task {
    /**
     * Constructs a Todo object with the given description.
     * 
     * @param description The description of the todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the todo task.
     * 
     * @return A string representation of the todo task.
     */
    @Override
    public String toString() {
        return "[T][" + this.getStatus().getStatusIcon() + "] " + this.getDescription() + "\n";
    }

    /**
     * Encodes the todo task into a string representation.
     * 
     * @return The encoded string representation of the todo task.
     */
    @Override
    public String encode() {
        String status = this.getStatus().isDone() ? "1" : "0";
        return "T | " + status + " | " + this.description;
    }
}
