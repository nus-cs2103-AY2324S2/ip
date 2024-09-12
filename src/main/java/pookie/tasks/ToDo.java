package pookie.tasks;

/**
 * Represents a todo task.
 */
public class ToDo extends Task {

    /**
     * Constructor for todo task.
     * @param description The description of the task.
     */
    public ToDo(String description)  {
        super(description);
    }

    /**
     * Returns the string representation of the todo task.
     * @return The string representation of the todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns the string representation of the todo task to be written to file.
     * @return The string representation of the todo task to be written to file.
     */
    @Override
    public String writeToFileString() {
        return "todo " + this.description;
    }
}
