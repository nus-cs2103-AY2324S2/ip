package aegis;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * Event class represents an event type of task. An event object contains
 * two LocalDate fields that store the start and end date of the task.
 */
public class Event extends Task {
    protected LocalDate start;
    protected LocalDate end;

    /**
     * Constructor for creating an event task.
     *
     * @param description Description of the event task.
     * @param start Start date of the event task.
     * @param end End date of the event task.
     */
    public Event(String description, String start, String end) {
        super(description);
        this.start = LocalDate.parse(start);
        this.end = LocalDate.parse(end);
    }

    /**
     * Returns the event task as a string containing its task type, completion status,
     * description, start and end dates.
     *
     * @return Event task as a string.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " +this.generateStartDateString() + " to: "
                + this.generateEndDateString() + ")";
    }

    /**
     * Returns a string in a format for saving to file.
     * String is formatted for easy parsing when event task is reconstructed using contents
     * of the string.
     *
     * @return String formatted for saving to file.
     */
    @Override
    public String toTaskSaveString() {
        return "E|" + this.getStatusInt() + "|" + this.description + "|" + this.generateStartSaveString()
                + "|" + this.generateEndSaveString();
    }

    /**
     * Returns the start date of the event task in a format for better readability.
     * Format follows MMM dd yyyy e.g. Jan 02 2024.
     *
     * @return Start date of event task in format for better readability.
     */
    private String generateStartDateString() {
        return this.start.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    /**
     * Returns the end date of the event task in a format for better readability.
     * Format follows MMM dd yyyy e.g. Jan 02 2024.
     *
     * @return End date of event task in format for better readability.
     */
    private String generateEndDateString() {
        return this.end.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    /**
     * Returns the start date of the event task in a format for saving to file.
     * Format follows yyyy-MM-dd e.g. 2024-01-02.
     *
     * @return Start date of event task in format for saving to file.
     */
    private String generateStartSaveString() {
        return this.start.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    /**
     * Returns the end date of the event task in a format for saving to file.
     * Format follows yyyy-MM-dd e.g. 2024-01-02.
     *
     * @return End date of event task in format for saving to file.
     */
    private String generateEndSaveString() {
        return this.end.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}
