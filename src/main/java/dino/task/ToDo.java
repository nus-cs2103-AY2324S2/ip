package dino.task;

/** Represents a Task without a deadline. */
public class ToDo extends Task {

    /**
     * Constructs a new ToDo task with the given description.
     *
     * @param description   The description of the task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the ToDo task.
     *
     * @return A formatted string representing the task's status, description.
     */
    public String toString() {
        return String.format(" T | %s | %s",
                getStatusIcon(),
                description);
    }
}
