/**
 * Represents a to-do item, which is a specialized form of Task.
 */
public class Todo extends Task {
    private static final String TODO_PREFIX = "[T]";

    /**
     * Constructs a new To-do with the given description.
     *
     * @param description The description of the to-do item.
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String convertTaskToFileString() {
        return String.format("T|%s|%s", super.getIsDone() ? "1" : "0", this.description.trim());
    }

    /**
     * Returns a string representation of the To-do.
     * The string includes the To-do identifier, followed by the Task's string representation.
     */
    @Override
    public String toString() {
        return TODO_PREFIX + super.toString();
    }
}
