package task;

import utilities.DateTime;

/**
 * A task with a start date and an end date.
 */
public class Event extends Task {
    /**
     * The date time of the start of the event as a string.
     */
    private String from;
    /**
     * The date time of the end of the event as a string.
     */
    private String to;
    /**
     * The date time of the start of the event as a date time object.
     */
    private DateTime start;
    /**
     * The date time of the end of the event as a date time object.
     */
    private DateTime end;

    /**
     * Event class constructor.
     * @param description The task description.
     * @param from The date time of the start of the event.
     * @param to The date time of the end of the event.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
        this.start = new DateTime(from);
        this.end = new DateTime(to);
    }

    /**
     * Event class constructor.
     * @param description The task description.
     * @param isDone A variable that determines whether a task has been completed.
     * @param from The date time of the start of the event.
     * @param to The date time of the end of the event.
     */
    public Event(String description, boolean isDone, String from, String to) {
        super(description, isDone);
        this.start = new DateTime(from);
        this.end = new DateTime(to);
    }

    /**
     * Outputs the task as a string to the user.
     * @return A string representing the task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + this.start.formatDate() + "hrs to: "
                + this.end.formatDate() + "hrs)";
    }

    /**
     * Outputs the task as a string to be stored in the save file.
     * @return A string representing the task to be saved.
     */
    @Override
    public String toFileString() {
        return "E" + super.toFileString() + "|" + this.from + "|" + this.to;
    }

    /**
     * Gets the description, start date and end date of the task.
     * @return The task description, start date and end date.
     */
    @Override
    public String descriptionToString() {
        return super.descriptionToString()
                + " (from: " + this.start.formatDate() + "hrs to: "
                + this.end.formatDate() + "hrs)";
    }
}
