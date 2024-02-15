package duke.tasks;

import java.time.LocalDate;

import duke.utils.Parser;


/**
 * Class represent Task type Event.
 */
public class Event extends Task {
    private LocalDate start;
    private LocalDate by;

    /**
     * Initializes an Event object with given params.
     *
     * @param status True for completed, False for not completed yet.
     * @param detail Detail information of the task.
     * @param start  task start time.
     * @param by     task end time.
     */
    public Event(Boolean status, String detail, LocalDate start, LocalDate by) {
        super(status, detail);
        this.start = start;
        this.by = by;
    }

    /**
     * Formats object to be stored in file.
     *
     * @return Formatted string to be stored in file.
     */
    @Override
    public String inFileStringFormat() {
        return "E|" + super.inFileStringFormat() + "|" + start
            + "|" + by;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + Parser.FORMATER.dateToString(start)
            + " to: " + Parser.FORMATER.dateToString(by) + ")";
    }

    public LocalDate getStart() {
        return start;
    }

    public LocalDate getBy() {
        return by;
    }
}
