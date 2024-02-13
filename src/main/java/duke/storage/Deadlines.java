package duke.storage;

/**
 * The Deadlines class represents a deadline task in the Duke task manager, which is a subtype of the Task class.
 * It inherits properties and methods from the Task class and provides a specific implementation for deadline tasks
 * with date and time details.
 */
public class Deadlines extends Task {

    protected String byDate = "";
    protected String byTime = "";

    /**
     * Constructs a Deadlines object with the specified original command, description, and date-time details.
     *
     * @param originalCommand The original command used to create the deadline task.
     * @param description     The description of the deadline task.
     * @param dateTimeBy      The deadline date and time in string format.
     */
    public Deadlines(String originalCommand, String description, String dateTimeBy) {
        super(originalCommand, description);

        String[] splitBy = dateTimeBy.split("-");
        int lenBy = splitBy.length;
        this.byDate = splitBy[0];

        if (lenBy > 1) {
            if (splitBy[1] != null) {
                this.byTime = splitBy[1];
            }
        }

    }

    /**
     * Returns a string representation of the deadline task, including its specific type identifier, the result of the
     * superclass's toString method, and date-time details.
     *
     * @return A string representation of the deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + "\n ( by: "
                + this.byDate
                + " "
                + (this.byTime == "" ? "" : this.byTime)
                + ")";
    }
}
