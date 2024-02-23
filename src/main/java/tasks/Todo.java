package tasks;

/**
 * The `Todo` class represents a task without a specific deadline or time period.
 * It extends the generic `Task` class and includes methods to get the type icon and
 * the command format for saving to a file.
 */
public class Todo extends Task {

    /**
     * Constructs a `Todo` object with the specified description, inheriting from the `Task` class.
     *
     * @param description The description of the todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Gets the type icon for the todo task.
     *
     * @return The type icon for todo tasks (default is "T").
     */
    @Override
    public String getTypeIcon() {
        return "T";
    }

    /**
     * Gets the command format for saving the todo task to a file.
     *
     * @return The command format for saving the todo task to a file.
     */
    @Override
    public String getCommand() {
        return String.format("todo %s\n%b\n", this.description, this.isDone);
    }
}
