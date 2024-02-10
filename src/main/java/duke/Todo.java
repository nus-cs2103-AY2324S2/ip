package duke;

/**
 * Todo class that extends the Task class
 * Represents a task without any date/time attached to it
 */
public class Todo extends Task {

    /**
     * Constructs a new Todo instance
     *
     * @param description The description of the task
     *
     *
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the Todo instance
     *
     * @return A string that represents the Todo instance, prefixed with "T"
     */
    @Override
    public String toString() {
        return "T" + super.toString();
    }
}