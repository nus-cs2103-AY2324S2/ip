package duke.task;

/**
 * The Todo class represents a simple todo task in the Duke application.
 * It extends the Task class and includes additional functionality to handle todo tasks.
 */
public class Todo extends Task {

    /**
     * Constructs a Todo object with the specified description and completion status.
     *
     * @param description The description of the todo task.
     * @param isComplete  The completion status of the task.
     */
    public Todo(String description, boolean isComplete) {
        super(description);
        this.isComplete = isComplete;
    }

    /**
     * Converts the Todo object to a format suitable for saving to a file.
     *
     * @return The Todo object formatted as a string for file storage.
     */
    @Override
    public String toFileFormat() {
        return String.format("T | %d | %s", isComplete ? 1 : 0, description);
    }


    /**
     * Returns a string representation of the Todo object.
     *
     * @return A string containing the task type, completion status, and description.
     */
    @Override
    public String toString() {
        return "T | " + (isComplete ? 1 : 0) + " | " + description;
    }
}
