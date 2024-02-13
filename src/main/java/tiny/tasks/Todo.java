package tiny.tasks;

/**
 * Represents a todo class.
*/
public class Todo extends Task {

    /**
     * Initializes Todo.
     *
     * @param description Description of the task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Initializes Todo.
     *
     * @param description Description of the task.
     * @param isDone      Status of the task.
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Formats the task into the correct format to save.
     *
     * @return String of the task in the correct format to save.
     */       
    @Override
    public String formatTasksForSaving() {
        return "T" + super.formatTasksForSaving();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
