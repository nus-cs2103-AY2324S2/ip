package Kokbot.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event task
 */
public class Event extends Task {

    /**
     * Start date of the Event
     */
    protected LocalDateTime startDate;

    /**
     * End date of the Event
     */
    protected LocalDateTime endDate;

    /**
     * Constructor for Event with LocalDateTime
     *
     * @param description  Description of the Event
     * @param newStartDate Start date of the Event in LocalDateTime format
     * @param newEndDate   End date of the Event in LocalDateTime format
     */
    public Event(String description, LocalDateTime newStartDate, LocalDateTime newEndDate) {
        super(description);
        this.startDate = newStartDate;
        this.endDate = newEndDate;
    }

    /**
     * Constructor for Event with String
     *
     * @param description  Description of the Event
     * @param newStartDate Start date of the Event in String format
     * @param newEndDate   End date of the Event in String format
     */
    public Event(String description, String newStartDate, String newEndDate) {
        super(description);
        this.startDate = LocalDateTime.parse(newStartDate);
        this.endDate = LocalDateTime.parse(newEndDate);
    }

    /**
     * Returns the type of the Event
     *
     * @return Type of the Event
     */
    @Override
    public String getType() {
        return "E";
    }

    public LocalDateTime getDateTime() {
        return this.startDate;
    }
    /**
     * Returns the start date of the Event in String format
     */
    public String formatStartDate() {
        return this.startDate.format(DateTimeFormatter.ofPattern(this.dateTimeFormat));
    }

    /**
     * Returns the end date of the Event in String format
     */
    public String formatEndDate() {
        return this.endDate.format(DateTimeFormatter.ofPattern(this.dateTimeFormat));
    }


    /**
     * Represents the Event in String format
     *
     * @return String format of the Event
     */
    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), this.formatStartDate(), this.formatEndDate());
    }

    /**
     * Represents the Event in String format for saving to file
     *
     * @return String format of the Event for saving to file
     */
    @Override
    public String toFileString() {
        return String.format("E,%s,%s,%s", super.toFileString(), this.startDate, this.endDate);
    }
}
