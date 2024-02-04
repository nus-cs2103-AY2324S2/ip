package ukecat;

/**
 * Represents a ToDo task in the UkeCat application.
 * Inherits from the Task class and includes additional methods specific to ToDo tasks.
 */
public class ToDo extends Task {

    /**
     * Constructs a new ToDo task with the specified description.
     *
     * @param description The description of the ToDo task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Constructs a new ToDo task with the specified status and description.
     *
     * @param isDone      The status of the ToDo task ("0" for not done, "1" for done).
     * @param description The description of the ToDo task.
     */
    public ToDo(String isDone, String description) {
        super(isDone, description);
    }

    /**
     * Returns a string representation of the ToDo task.
     * The string includes the task type, status icon, and description.
     *
     * @return A string representation of the ToDo task.
     */
    @Override
    public String toString() {
        return String.format("[T]%s%s", this.getStatusIcon(), super.toString());
    }
}
