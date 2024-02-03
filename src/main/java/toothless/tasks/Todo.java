package toothless.tasks;

/**
 * Represents a todo task in the Toothless application. A todo task includes only a description
 * and does not have a date associated with it.
 */
public class Todo extends Task {

    /**
     * Constructs a Todo task with the specified description.
     *
     * @param description The todo's description.
     */
    public Todo(String description) {
        super.description = description;
    }

    /**
     * Constructs a Todo task with the specified description and completion status.
     *
     * @param description The todo's description.
     * @param isDone The todo's completion status.
     */
    public Todo(String description, boolean isDone) {
        super.description = description;
        super.isDone = isDone;
    }

    public String getTaskIcon() {
        return "T";
    }

    @Override
    public String toWrite() {
        return "T | " + super.toWrite();
    }
}
