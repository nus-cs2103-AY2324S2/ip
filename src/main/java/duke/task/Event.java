package duke.task;

import duke.Duke;
import duke.DukeException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * A type of task with a LocalDate variable from and to, to represent the event starting and
 * end times.
 */
public class Event extends Task {
    private LocalDate from;
    private LocalDate to;

    public Event(String desc, String from, String to) {
        super(desc);
        this.from = LocalDate.parse(from);
        this.to = LocalDate.parse(to);
    }

    public Event(String desc, boolean isDone, String from, String to) throws DukeException {
        super(desc, isDone);
        try {
            this.from = LocalDate.parse(from);
            this.to = LocalDate.parse(to);
        } catch (DateTimeParseException ex) {
            throw new DukeException("Oops! The date and time format you provided is not valid. Please enter in the format yyyy-MM-dd");
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else if (obj instanceof Event event) {
            return super.equals(event) && from.equals(event.from) && to.equals(event.to);
        } else {
            return false;
        }
    }

    public String toStore() {
        // need to store status as well
        return "E | " + super.toStore() + " | " + from + " | " + to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() +
                " (from: " + from.format(DateTimeFormatter.ofPattern("MMM d yyyy")) +
                " to: " + to.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
