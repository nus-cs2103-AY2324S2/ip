package duke.task;

/**
 * Represents the todo task.
 */
public class Todo extends Task {

    /**
     * Instantiates a todo task.
     *
     * @param description Represents the string describing what the task is.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Instantiates a todo task with a specific checkmark.
     *
     * @param description Represents the string describing what the task is.
     * @param isDone Represents the boolean describing whether the task is checked.
     */
    public Todo(String description, Boolean isDone) {
        super(description, isDone);
    }

    /**
     * Returns a string formatting of the todo task.
     *
     * @return A string representation of the todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a special string formmating of the todo task to write into local file.
     *
     * @return A special string representation of the todo task used in the local file.
     */
    @Override
    public String toSave() {
        String strDone = this.isDone ? "1" : "0";
        return "T|" + strDone + "|" + this.description;
    }
}
