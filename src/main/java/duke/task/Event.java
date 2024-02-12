package duke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * The `Event` class represents a task that have a start date time and an end date time.
 * It provides methods to create strings related to task's details.
 * It extends the `Task` class.
 */
public class Event extends Task {

    protected LocalDate fromDate;
    protected LocalDateTime fromDateTime;

    protected LocalDate toDate;
    protected LocalDateTime toDateTime;

    /**
     * Creates a `Event` task with description and due date.
     *
     * @param description The task's description.
     * @param fromDate The task's start date.
     * @param toDate The task's end date.
     */
    public Event(String description, LocalDate fromDate, LocalDate toDate) {
        super(description);
        this.fromDate = fromDate;
        this.fromDateTime = null;
        this.toDate = toDate;
        this.toDateTime = null;
    }

    /**
     * Creates a `Event` task with description and due date.
     *
     * @param description The task's description.
     * @param fromDateTime The task's start date and time.
     * @param toDate The task's end date.
     */
    public Event(String description, LocalDateTime fromDateTime, LocalDate toDate) {
        super(description);
        this.fromDate = null;
        this.fromDateTime = fromDateTime;
        this.toDate = toDate;
        this.toDateTime = null;
    }

    /**
     * Creates a `Event` task with description and due date.
     *
     * @param description The task's description.
     * @param fromDate The task's start date.
     * @param toDateTime The task's end date and time.
     */
    public Event(String description, LocalDate fromDate, LocalDateTime toDateTime) {
        super(description);
        this.fromDate = fromDate;
        this.fromDateTime = null;
        this.toDate = null;
        this.toDateTime = toDateTime;
    }

    /**
     * Creates a `Event` task with description and due date.
     *
     * @param description The task's description.
     * @param fromDateTime The task's start date and time.
     * @param toDateTime The task's end date and time.
     */
    public Event(String description, LocalDateTime fromDateTime, LocalDateTime toDateTime) {
        super(description);
        this.fromDate = null;
        this.fromDateTime = fromDateTime;
        this.toDate = null;
        this.toDateTime = toDateTime;
    }

    /**
     * Creates a `Event` task with description and due date.
     *
     * @param description The task's description.
     * @param isDone The task's done status.
     * @param fromDate The task's start date.
     * @param toDate The task's end date.
     */
    public Event(String description, boolean isDone, LocalDate fromDate, LocalDate toDate) {
        super(description, isDone);
        this.fromDate = fromDate;
        this.fromDateTime = null;
        this.toDate = toDate;
        this.toDateTime = null;
    }

    /**
     * Creates a `Event` task with description and due date.
     *
     * @param description The task's description.
     * @param isDone The task's done status.
     * @param fromDateTime The task's start date and time.
     * @param toDate The task's end date.
     */
    public Event(String description, boolean isDone, LocalDateTime fromDateTime, LocalDate toDate) {
        super(description, isDone);
        this.fromDate = null;
        this.fromDateTime = fromDateTime;
        this.toDate = toDate;
        this.toDateTime = null;
    }

    /**
     * Creates a `Event` task with description and due date.
     *
     * @param description The task's description.
     * @param isDone The task's done status.
     * @param fromDate The task's start date.
     * @param toDateTime The task's end date and time.
     */
    public Event(String description, boolean isDone, LocalDate fromDate, LocalDateTime toDateTime) {
        super(description, isDone);
        this.fromDate = fromDate;
        this.fromDateTime = null;
        this.toDate = null;
        this.toDateTime = toDateTime;
    }

    /**
     * Creates a `Event` task with description and due date.
     *
     * @param description The task's description.
     * @param isDone The task's done status.
     * @param fromDateTime The task's start date and time.
     * @param toDateTime The task's end date and time.
     */
    public Event(String description, boolean isDone, LocalDateTime fromDateTime, LocalDateTime toDateTime) {
        super(description, isDone);
        this.fromDate = null;
        this.fromDateTime = fromDateTime;
        this.toDate = null;
        this.toDateTime = toDateTime;
    }

    /**
     * Creates a string representing a number with at least two digits.
     *
     * @param s The input number
     * @return String representing the input number with at least two digits.
     */
    private String padZero(int s) {
        if (s < 10) {
            return "0" + s;
        }
        return Integer.toString(s);
    }

    /**
     * Creates a formatted string to store the start date and time.
     *
     * @return The formatted string to store the start date and time.
     */
    public String getEventFromForSave() {
        return this.fromDate == null
                ? (this.fromDateTime.getYear() + "-" + padZero(this.fromDateTime.getMonthValue())
                + "-" + padZero(this.fromDateTime.getDayOfMonth()) + " " + padZero(this.fromDateTime.getHour()) + ":"
                + padZero(this.fromDateTime.getMinute()))
                : (this.fromDate.getYear() + "-" + padZero(this.fromDate.getMonthValue())
                + "-" + padZero(this.fromDate.getDayOfMonth()));
    }

    /**
     * Creates a formatted string to store the end date and time.
     *
     * @return The formatted string to store the end date and time.
     */
    public String getEventToForSave() {
        return this.toDate == null
                ? (this.toDateTime.getYear() + "-" + padZero(this.toDateTime.getMonthValue())
                + "-" + padZero(this.toDateTime.getDayOfMonth()) + " " + padZero(this.toDateTime.getHour()) + ":"
                + padZero(this.toDateTime.getMinute()))
                : (this.toDate.getYear() + "-" + padZero(this.toDate.getMonthValue())
                + "-" + padZero(this.toDate.getDayOfMonth()));
    }

    /**
     * Creates a formatted string to display the start date and time.
     *
     * @return The formatted string to display the start date and time.
     */
    public String getEventFromForDisplay() {
        return this.fromDate == null
                ? (this.fromDateTime.getMonth() + " " + padZero(this.fromDateTime.getDayOfMonth())
                + " " + this.fromDateTime.getYear() + " " + padZero((this.fromDateTime.getHour() > 12
                ? this.fromDateTime.getHour() - 12 : this.fromDateTime.getHour())) + ":"
                + padZero(this.fromDateTime.getMinute()) + (this.fromDateTime.getHour() >= 12 ? "PM" : "AM"))
                : (this.fromDate.getMonth() + " " + padZero(this.fromDate.getDayOfMonth())
                + " " + this.fromDate.getYear());
    }

    /**
     * Creates a formatted string to display the end date and time.
     *
     * @return The formatted string to display the end date and time.
     */
    public String getEventToForDisplay() {
        return this.toDate == null
                ? (this.toDateTime.getMonth() + " " + padZero(this.toDateTime.getDayOfMonth())
                + " " + this.toDateTime.getYear() + " " + padZero((this.toDateTime.getHour() > 12
                ? this.toDateTime.getHour() - 12 : this.toDateTime.getHour())) + ":"
                + padZero(this.toDateTime.getMinute()) + (this.toDateTime.getHour() >= 12 ? "PM" : "AM"))
                : (this.toDate.getMonth() + " " + padZero(this.toDate.getDayOfMonth())
                + " " + this.toDate.getYear());
    }

    /**
     * Returns the formatted string containing done status and description of the task.
     *
     * @return The formatted string containing done status and description of the task.
     */
    @Override
    public String getDescriptionStatus() {
        return "[E]" + (isDone ? "[X] " : "[ ] ") + this.description + " (from: " + this.getEventFromForDisplay()
                + " to: " + this.getEventToForDisplay() + ")";
    }

    /**
     * Returns various fields of the task.
     *
     * @return A string array containing the description, done status, and formatted due date and time of a task.
     */
    @Override
    public String[] getFields() {
        String[] results = new String[4];
        results[0] = this.description;
        results[1] = this.isDone ? "Y" : "N";
        results[2] = this.getEventFromForSave();
        results[3] = this.getEventToForSave();
        return results;
    }
}
