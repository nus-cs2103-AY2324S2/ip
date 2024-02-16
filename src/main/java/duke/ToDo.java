package duke;

/**
 * ToDo class represents a todo type of task. A todo object does not contain
 * any additional fields on top of the description field already present in
 * its superclass Task.
 */
public class ToDo extends Task {
    /**
     * Constructor for creating a ToDo object
     * @param description Description of the todo task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns the todo task as a string containing its task type, completion status
     * and description.
     *
     * @return Todo task as a string.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a string in a format for saving to file.
     * String is formatted for easy parsing when todo task is reconstructed using contents
     * of the string.
     *
     * @return String formatted for saving to file.
     */
    @Override
    public String toTaskSaveString() {
        return "T|" + this.getStatusInt() + "|" + this.description;
    }
}
