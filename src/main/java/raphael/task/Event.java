package raphael.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import raphael.command.Command;
import raphael.exception.RaphaelException;

/**
 * The event class.
 */
public class Event extends Task {
    private final LocalDateTime from;
    private final LocalDateTime to;
    private final DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    private final DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");

    /**
     * The constructor of the event class.
     *
     * @param description the description of the event.
     * @param from the datetime which this event starts.
     * @param to the datetime which this event ends.
     * @throws RaphaelException the exception exclusive to Raphael.
     */

    public Event(String description, String from, String to) throws RaphaelException {
        super(description);
        try {
            this.from = LocalDateTime.parse(from, this.inputFormat);
            this.to = LocalDateTime.parse(to, this.inputFormat);
        } catch (DateTimeParseException e) {
            throw new RaphaelException(RaphaelException.invalidFormat(Command.Type.EVENT));
        }
    }

    /**
     * The overridden constructor for the event class.
     *
     * @param description the description of the event.
     * @param from the datetime of the event starts.
     * @param to the datetime of the event ends.
     * @param isDone the status of the event when read from file.
     * @throws RaphaelException the exception exclusive to Raphael.
     */
    public Event(String description, String from, String to, boolean isDone) throws RaphaelException {
        super(description, isDone);
        try {
            this.from = LocalDateTime.parse(from, this.inputFormat);
            this.to = LocalDateTime.parse(to, this.inputFormat);
        } catch (DateTimeParseException e) {
            throw new RaphaelException(RaphaelException.invalidFormat(Command.Type.EVENT));
        }
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(),
                this.from.format(this.outputFormat),
                this.to.format(this.outputFormat));
    }
    @Override
    public String toFileFormat() {
        return String.format("E |&| %s |&| %s |&| %s", super.toFileFormat(),
                this.from.format(this.inputFormat), this.to.format(this.inputFormat));
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Event)) {
            return false;
        }
        Event eventToCompare = (Event) o;
        return this.from.equals(eventToCompare.from)
                && this.to.equals(eventToCompare.to)
                && super.equals(eventToCompare);
    }
}
