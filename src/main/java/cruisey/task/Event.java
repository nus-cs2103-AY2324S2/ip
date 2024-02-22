package cruisey.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import cruisey.exception.DukeException;
import cruisey.ui.Ui;

/**
 * The Event class represents a task that spans a specific duration.
 * It is a subclass of the Task class and includes information about the start and end times.
 */
public class Event extends Task {
    /** The LocalDateTime representing the start time of the event. */
    protected LocalDateTime from;

    /** The string representation of the start time (used when parsing or if LocalDateTime parsing fails). */
    protected String fromString;

    /** The LocalDateTime representing the end time of the event. */
    protected LocalDateTime to;

    /** The string representation of the end time (used when parsing or if LocalDateTime parsing fails). */
    protected String toString;
    protected TaskPriority priority;

    private Ui ui;

    /**
     * Constructs an Event object with the specified description, start time string, and end time string.
     *
     * @param description The description of the event.
     * @param fromString   The string representation of the start time.
     * @param toString     The string representation of the end time.
     * @param priority    The priority of the task.
     * @throws DukeException If there are issues with the provided description or duration.
     */
    public Event(String description, String fromString, String toString, Ui ui, TaskPriority priority)
            throws DukeException {
        super(TaskType.E, description, priority);
        this.fromString = fromString.trim();
        this.toString = toString.trim();
        this.ui = ui;
        this.priority = priority;
        initializeEvent();
    }

    /**
     * Initializes the event by parsing the "from" and "to" date-time strings using the specified date-time format.
     * Validates the inputs and ensures that either LocalDateTime or its corresponding string representation is
     * non-null.
     *
     * @throws DukeException If there is an issue with the date-time parsing or if the inputs are invalid.
     */
    public void initializeEvent() throws DukeException {
        try {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
            this.from = LocalDateTime.parse(fromString, dateTimeFormatter);
            this.to = LocalDateTime.parse(toString, dateTimeFormatter);
        } catch (DateTimeParseException e) {
            this.from = null;
            this.to = null;
        }

        validateInputs();

        assert (this.from != null || !this.fromString.isEmpty()) : "Either LocalDateTime or fromString should be"
                + " non-null.";
        assert (this.to != null || !this.toString.isEmpty()) : "Either LocalDateTime or toString should be"
                + " non-null.";
        assert !(this.from != null && !this.fromString.isEmpty()) : "Both LocalDateTime and fromString should not"
                + " be non-null.";
        assert !(this.to != null && !this.toString.isEmpty()) : "Both LocalDateTime and toString should not "
                + "be non-null.";
    }

    /**
     * Gets the representation of the start time (either LocalDateTime or the original string).
     *
     * @return The start time as an Object (either LocalDateTime or String).
     */
    public Object getFrom() {
        return (this.from != null) ? this.from : this.fromString;
    }
    /**
     * Gets the representation of the end time (either LocalDateTime or the original string).
     *
     * @return The end time as an Object (either LocalDateTime or String).
     */
    public Object getTo() {
        return (this.to != null) ? this.to : this.toString;
    }

    /**
     * Validates the inputs to ensure that required information is provided.
     *
     * @throws DukeException If validation fails.
     */
    private void validateInputs() throws DukeException {
        if ((from == null && to == null) && (fromString.isEmpty() && toString.isEmpty())) {
            Ui.showError("You did not mention the duration! Please re-enter correctly!");
        } else if (from == null && fromString.isEmpty()) {
            Ui.showError("You did not mention from when! Please re-enter correctly!");
        } else if (to == null && toString.isEmpty()) {
            Ui.showError("You did not mention till when! Please re-enter correctly!");
        } else if (description.isEmpty()) {
            Ui.showError("You didn't specify the event!");
        }

        assert (from == null && to == null) || (!fromString.isEmpty() && !toString.isEmpty())
                : "Either both LocalDateTime objects (from and to) should be null, or both fromString and toString "
                        + "should be non-empty.";
        assert !(from == null && fromString.isEmpty()) : "Either from LocalDateTime object should be non-null, or "
                + "fromString should be non-empty.";
        assert !(to == null && toString.isEmpty()) : "Either to LocalDateTime object should be non-null, or toString"
                + " should be non-empty.";
        assert description != null : "Description should not be null.";
    }


    /**
     * Gets the LocalDateTime representation of the start time.
     *
     * @return The start time as a LocalDateTime object.
     */
    public LocalDateTime getFromTime() {
        return this.from;
    }

    /**
     * Gets the LocalDateTime representation of the end time.
     *
     * @return The end time as a LocalDateTime object.
     */
    public LocalDateTime getToTime() {
        return this.to;
    }

    /**
     * Gets the string representation of the start time.
     *
     * @return The start time as a string.
     */
    public String getFromString() {
        return this.fromString;
    }

    /**
     * Gets the string representation of the end time.
     *
     * @return The end time as a string.
     */
    public String getToString() {
        return this.toString;
    }

    /**
     * Overrides the toString method to provide a formatted string representation of the Event task.
     *
     * @return Formatted string representation of the Event task.
     */
    public String getMessage() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        String fromStringFormatted = (from != null)
                ? " from: " + from.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"))
                : (this.fromString != null ? " from: " + this.fromString : "");

        String toStringFormatted = (to != null)
                ? " to: " + to.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"))
                : (this.toString != null ? " to: " + this.toString : "");

        String priorityFormatted = (priority != null) ? " [Priority: " + priority + "]" : "";

        return ui.printMessage("Coool, I've added this task:\n [E][" + getStatusIcon() + "] " + getDescription()
                + fromStringFormatted + toStringFormatted + priorityFormatted);
    }
}
