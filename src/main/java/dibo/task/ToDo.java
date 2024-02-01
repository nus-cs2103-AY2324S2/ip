package dibo.task;

/**
 * Class to represent a to-do task.
 */
public class ToDo extends Task {

    /**
     * Constructor for the ToDo class.
     *
     * @param description The description of the task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns the string representation for the to-do task,
     * usually for the display format in the ui.
     *
     * @return The string representation of the to-do task for displaying.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns the string representation of the to-do task,
     * usually for saving in the text file.
     *
     * @return The string representation of the to-do task for saving.
     */
    @Override
    public String getSaveFormat() {
        return "todo | " + super.getSaveFormat();
    }
}
