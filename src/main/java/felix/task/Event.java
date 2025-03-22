package felix.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import felix.exception.FelixException;

/**
 * Class representing a task with a start and end time
 */
public class Event extends Task {
    private final LocalDateTime start;
    private final LocalDateTime end;

    /**
     * Constructor for Event class.
     *
     * @param description Description of task.
     * @param start Date and time when task begins
     * @param end Date and time when task ends
     */
    public Event(String description, String start, String end) {
        super(description);
        this.start = LocalDateTime.parse(start, INPUT_FORMATTER);
        this.end = LocalDateTime.parse(end, INPUT_FORMATTER);
    }

    /**
     * Returns a new Event instance with updated description, start and end.
     * @param paramString String containing new description, new start, and new end
     */
    @Override
    public Event updateTask(String paramString) throws FelixException {
        try {
            String[] params = paramString.split(" /from | /to ");
            return new Event(params[0], params[1], params[2]);
        } catch (IndexOutOfBoundsException e) {
            throw new FelixException("Command does not follow this format: update {index}" +
                    " {description} /from {start_datetime} /to {end_datetime}");
        } catch (DateTimeParseException e) {
            throw new FelixException("datetime not in the format \"yyyy-MM-dd HHmm\"");
        }
    }

    /**
     * Returns the String representation of the Deadline instance to be written to file.
     */
    @Override
    public String toFileString() {
        return String.format("E | %s | %s | %s | %s", this.getStatusIcon(), this.getDescription(),
                this.start.format(INPUT_FORMATTER), this.end.format(INPUT_FORMATTER));
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(),
                this.start.format(OUTPUT_FORMATTER), this.end.format(OUTPUT_FORMATTER));
    }
}
