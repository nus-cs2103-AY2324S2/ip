package duke.task;

/**
 * Represents the event task.
 */
public class Event extends Task {
    /**
     * Represents the start time of the event.
     */
    protected String start;

    /**
     * Represents the end time of the event.
     */
    protected String end;

    /**
     * Instantiates an event task.
     *
     * @param description Represents the string describing what the task is.
     * @param start Represents the start time of the event.
     * @param end Represents the end time of the event.
     */
    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    /**
     * Instantiates an event task with a specific checkmark.
     *
     * @param description Represents the string describing what the task is.
     * @param start Represents the start time of the event.
     * @param end Represents the end time of the event.
     * @param isDone Represents the boolean describing whether the task is checked.
     */
    public Event(String description, String start, String end, Boolean isDone) {
        super(description, isDone);
        this.start = start;
        this.end = end;
    }

    /**
     * Returns a string formatting of the events task.
     *
     * @return A string representation of the events task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.start + " to: " + this.end + ")";
    }

    /**
     * Returns a special string formmating of the events task to write into local file.
     *
     * @return A special string representation of the events task used in the local file.
     */
    @Override
    public String toSave() {
        String strDone = this.isDone ? "1" : "0";
        return "E|" + strDone + "|" + this.description + "|" + this.start + "|" + this.end;
    }

    /**
     * Updates the start parameter of this class.
     *
     * @param newStart The new value for start date.
     * @return A string representation of the work done.
     */
    public String updateStart(String newStart) {
        String response = "Updated " + this.description + "'s end date from: " + this.start + "to: " + newStart;
        this.start = newStart;
        return response;
    }

    /**
     * Updates the end parameter of this class.
     *
     * @param newEnd The new value for enddate.
     * @return A string representation of the work done.
     */
    public String updateEnd(String newEnd) {
        String response = "Updated " + this.description + "'s end date from: " + this.end + "to: " + newEnd;
        this.end = newEnd;
        return response;
    }
}
