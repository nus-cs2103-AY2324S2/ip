package duke.task;

<<<<<<< HEAD
/**
 * Represents a task of type ToDo in the Duke application.
 */
=======
>>>>>>> A-CodingStandard
public class ToDo extends Task {

    /**
     * Constructs a ToDo task with the specified description.
     *
     * @param description The description of the task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Gets the type code representing the task type.
     *
     * @return A string representing the task type code.
     */
    public String getType() {
        return "T";
    }

    /**
     * Converts the task to a string representation.
     *
     * @return A formatted string representing the task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
