package duke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;

import duke.command.CommandType;
import duke.exception.DukeException;
import duke.helpers.MyDateTime;

/**
 * duke.task.Event class with attributes from and to.
 */
public class Event extends Task {
    /** describe the start period. */
    private LocalDateTime from;
    /** describe the end period. */
    private LocalDateTime to;

    /**
     * Constructor of duke.task.Event.
     *
     * @param description to describe the event.
     * @param from period when the event start.
     * @param to period when the event end.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) throws DukeException {
        super(description);
        if (to.isBefore(from)) {
            throw new DukeException("Event end time should be after start time!");
        }
        this.from = from;
        this.to = to;
    }

    /**
     * Constructor of duke.task.Event.
     *
     * @param description to describe the event.
     * @param from period when the event start.
     * @param to period when the event end.
     * @param isDone
     */
    public Event(String description, LocalDateTime from, LocalDateTime to, boolean isDone) throws DukeException {
        super(description, isDone);
        if (to.isBefore(from)) {
            throw new DukeException("Event end time should be after start time!");
        }
        this.from = from;
        this.to = to;
    }

    /**
     * Returns String representation of event.
     *
     * @return string representation of event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + MyDateTime.englishFormatter(this.from)
                + " to: " + MyDateTime.englishFormatter(this.to) + ")";
    }

    /**
     * Returns String representation for storage.
     *
     * @return String representation for storage of duke.task.Event task.
     */
    @Override
    public String toStorageString() {
        return CommandType.EVENT.toString() + " , " + super.toStorageString() + " , "
                + MyDateTime.formatter(this.from) + " , " + MyDateTime.formatter(this.to);
    }

    /**
     * Returns boolean value of date is within event period.
     *
     * @param date
     * @return true if date is within event period.
     */
    @Override
    public boolean checkDate(LocalDate date) {
        LocalDate f = this.from.toLocalDate();
        LocalDate t = this.to.toLocalDate();

        return date.isAfter(f.minusDays(1)) && date.isBefore(t.plusDays(1));
    }
}
