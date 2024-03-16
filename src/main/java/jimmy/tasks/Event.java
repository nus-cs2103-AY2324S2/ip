package jimmy.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;

/**
 * Represents an event with a start and end time.
 */
public class Event extends Task {
    private final LocalDate start;
    private final LocalDate end;

    // this date format displays the date purely in numbers
    private final DateTimeFormatter informalDateFormat = DateTimeFormatter.ofPattern("d-MM-yyyy");
    // this date format displays the month in words
    private final DateTimeFormatter formalDateFormat = DateTimeFormatter.ofPattern("d MMM yyyy");

    /**
     * Constructor for Event class.
     *
     * @param taskName Name of the task.
     * @param start    Start time of the event.
     * @param end      End time of the event.
     */
    public Event(String taskName, String start, String end, boolean isCompleted)
            throws DateTimeParseException, IllegalArgumentException {
        super(taskName, isCompleted);
        this.start = parseStringtoLocalDate(start);
        this.end = parseStringtoLocalDate(end);

        if (!hasValidDuration(this.start, this.end)) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Returns the start time of the event.
     *
     * @return Start time of the event.
     */
    public LocalDate getStart() {
        return this.start;
    }

    /**
     * Returns the end time of the event.
     *
     * @return End time of the event.
     */
    public LocalDate getEnd() {
        return this.end;
    }

    /**
     * Checks if the start and end timing given by the user are valid
     *
     * @param start Start time of the event.
     * @param end   End time of the event.
     * @return true if the start and end timing are valid, false otherwise.
     */
    private boolean hasValidDuration(LocalDate start, LocalDate end) {
        boolean isStartBeforeEnd = start.isBefore(end);
        boolean isStartAfterNow = start.isAfter(LocalDate.now());
        boolean isEndAfterNow = end.isAfter(LocalDate.now());

        return isStartBeforeEnd && isStartAfterNow && isEndAfterNow;
    }

    /**
     * Details regarding the event.
     *
     * @return String representation of an event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + parseLocalDatetoString(this.start)
                + " to: " + parseLocalDatetoString(this.end) + ")";
    }

    /**
     * Format of the event to be saved in the file.
     *
     * @return String representation of an event.
     */
    public String toFileString() {
        return String.format("%s | %d | %s | %s | %s", "E", Objects.equals(super.getStatus(), "X") ? 1 : 0,
                super.getDesc(), this.start.format(informalDateFormat),
                this.end.format(informalDateFormat));
    }
}
