package duke.Task;

/**
 * Represents a ToDo task.
 * Inherits from the Task class.
 */
public class ToDoTask extends Task {

    /**
     * Constructs a ToDoTask object with the given description.
     *
     * @param description The description of the ToDo task.
     */
    public ToDoTask(String description) {
        super(description);
    }

    /**
     * Converts the ToDoTask object to a string representation for saving to a file.
     *
     * @return The string representation of the ToDoTask object for saving to a file.
     */
    @Override
    public String toFileString() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }

    /**
     * Converts the ToDoTask object to a string representation for displaying to the user.
     *
     * @return The string representation of the ToDoTask object for displaying to the user.
     */
    @Override
    public String toString() {
        return "[T][" + (isDone ? "X" : " ") + "] " + description;
    }
}