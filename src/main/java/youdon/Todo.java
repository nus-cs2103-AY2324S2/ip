package youdon;

/**
 * Represents a to-do task in the Youdon task management system.
 * Inherits from the Task class.
 */
public class Todo extends Task {

    /**
     * Constructs a new instance of the Todo class with the specified description.
     *
     * @param description The description of the to-do task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the type icon for the to-do task.
     *
     * @return The type icon for the to-do task (in this case, "T").
     */
    public String getTypeIcon() {
        return "T";
    }

    /**
     * Returns a string representation of the to-do task.
     *
     * @return A string representation of the to-do task, including its type icon, status icon, and description.
     */
    @Override
    public String toString() {
        return "[" + this.getTypeIcon() + "][" + this.getStatusIcon() + "] " + this.description.trim();
    }
}
