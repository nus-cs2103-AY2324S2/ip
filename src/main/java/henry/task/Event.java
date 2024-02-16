package henry.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import henry.HenryException;

/**
 * Represents an Event Task.
 */
public class Event extends Task {
    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");

    private LocalDateTime from;
    private LocalDateTime to;

    /**
     * Creates an Event object.
     *
     * @param description The description of the event.
     * @param from The start time of the event.
     * @param to The end time of the event.
     * @throws HenryException If the description, start time or end time is not specified.
     */
    public Event(String description, String from, String to) throws HenryException {
        super(description);

        if (from.isBlank()) {
            throw new HenryException("Missing time !!!\n");
        } else if (to.isBlank()) {
            throw new HenryException("Missing time!!!\n");
        }

        this.from = LocalDateTime.parse(from, INPUT_FORMATTER);
        this.to = LocalDateTime.parse(to, INPUT_FORMATTER);
    }

    /**
     * Sets the start datetime of the event.
     * @param from The start time of the event.
     * @throws HenryException If the start time is not specified.
     */
    public void setFrom(String from) throws HenryException {
        if (from.isBlank()) {
            throw new HenryException("Missing time !!!\n");
        }
        this.from = LocalDateTime.parse(from, INPUT_FORMATTER);
    }

    /**
     * Sets the end datetime of the event.
     */
    public void setTo(String to) throws HenryException {
        if (to.isBlank()) {
            throw new HenryException("Missing time !!!\n");
        }
        this.to = LocalDateTime.parse(to, INPUT_FORMATTER);
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(),
                from.format(OUTPUT_FORMATTER), to.format(OUTPUT_FORMATTER));
    }

    @Override
    public String toFileString() {
        return String.format("E | %s | %s | %s", super.toFileString(),
                from.format(INPUT_FORMATTER), to.format(INPUT_FORMATTER));
    }
}
