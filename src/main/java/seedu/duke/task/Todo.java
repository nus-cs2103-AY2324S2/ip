package seedu.duke.task;

/**
 * Represents a to do task created by the user. A <code>Todo</code> represents a task to be done by the user
 * with description of the task.
 */
public class Todo extends Task {
    public Todo(String input) {
        super(input);
    }

    /**
     * Constructor of the Todo object
     *
     * @param description The description of the todo
     * @param hasDone     whether the todo has done
     */
    public Todo(String description, Boolean hasDone) {
        super.setHasDone(hasDone);
        super.setDescription(description);
    }

    /**
     * Returns the string representation of the todo
     *
     * @return the string representation of the todo
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
