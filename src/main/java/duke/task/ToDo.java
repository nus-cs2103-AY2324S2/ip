package duke.task;

/**
 * Represents a todo-type task. It includes a description.
 * This class extends the Task class, adding functionality specific to tasks
 * that have a deadline.
 */
public class ToDo extends Task {

    /**
     * Constructs a new Todo instance with a specified description.
     * 
     * @param description The text description of the todo task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Generates a string representation of the event task, including its type
     * indicator "[E]", completion status, and description.
     * 
     * @return A string that represents the todo task, showing its status and
     *         description.
     */
    @Override
    public String toString() {
        return "[T] " + super.toString();
    }
}
