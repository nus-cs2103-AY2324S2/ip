package duke.storage;

/**
 * The Events class represents an event task in the Duke task manager, which is a subtype of the Task class.
 * It inherits properties and methods from the Task class and provides a specific implementation for event tasks
 * with date and time details.
 */
public class Events extends Task {

    protected String fromDate = "";
    protected String fromTime = "";
    protected String toDate = "";
    protected String toTime = "";

    /**
     * Constructs an Events object with the specified original command, description, and date-time details.
     *
     * @param originalCommand The original command used to create the event task.
     * @param description     The description of the event task.
     * @param dateTimeFrom    The starting date and time of the event in string format.
     * @param dateTimeTo      The ending date and time of the event in string format.
     */
    public Events(String originalCommand, String description, String dateTimeFrom, String dateTimeTo) {
        super(originalCommand, description);

        String[] splitFrom = dateTimeFrom.split("-");
        String[] splitTo = dateTimeTo.split("-");
        this.fromDate = splitFrom[0];
        this.toDate = splitTo[0];

        int lenFrom = splitFrom.length;
        int lenTo = splitTo.length;

        if (lenFrom > 1) {
            this.fromTime = splitFrom[1];
        }
        if (lenTo > 1) {
            this.toTime = splitTo[1];
        }

    }

    /**
     * Returns a string representation of the event task, including its specific type identifier, the result of the
     * superclass's toString method, and date-time details.
     *
     * @return A string representation of the event task.
     */
    @Override
    public String toString() {

        return "[E]" + super.toString() + "\n (from: "
                + this.fromDate
                + " "
                + (this.fromTime == "" ? "" : this.fromTime)
                + " to: "
                + this.toDate
                + " "
                + (this.toTime == "" ? "" : this.toTime)
                + ")";
    }
}
