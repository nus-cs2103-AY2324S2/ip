package duke.task;

import duke.exception.DukeException;
import duke.task.TaskType;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    protected LocalDateTime from;
    protected String fromString;
    protected LocalDateTime to;
    protected String toString;

    public Event(String description, String fromString, String toString) throws DukeException {
        super(TaskType.E, description);

        this.fromString = fromString.trim();
        this.toString = toString.trim();

        try {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
            this.from = LocalDateTime.parse(fromString, dateTimeFormatter);
            this.to = LocalDateTime.parse(toString, dateTimeFormatter);
        } catch (DateTimeParseException e) {
            this.from = null;
            this.to = null;
        }

        validateInputs();
    }

    public Object getFrom() {
        return (this.from != null) ? this.from : this.fromString;
    }
    public Object getTo() {
        return (this.to != null) ? this.to : this.toString;
    }

    private void validateInputs() throws DukeException {
        if ((from == null && to == null) && (fromString.isEmpty() && toString.isEmpty())) {
            throw new DukeException("You did not mention the duration! Please re-enter correctly!");
        } else if (from == null && fromString.isEmpty()) {
            throw new DukeException("You did not mention from when! Please re-enter correctly!");
        } else if (to == null && toString.isEmpty()) {
            throw new DukeException("You did not mention till when! Please re-enter correctly!");
        } else if (description.isEmpty()) {
            throw new DukeException("You didn't specify the event!");
        }
    }

    public LocalDateTime getFromTime() {
        return this.from;
    }

    public LocalDateTime getToTime() {
        return this.to;
    }

    public String getFromString() {
        return this.fromString;
    }

    public String getToString() {
        return this.toString;
    }


    @Override
    public String toString() {
        String fromStringFormatted = (from != null) ?
                " from: " + from.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) :
                (this.fromString != null ? " from: " + this.fromString : "");

        String toStringFormatted = (to != null) ?
                " to: " + to.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) :
                (this.toString != null ? " to: " + this.toString : "");

        return "Got it. I've added this task:\n [E][" + getStatusIcon() + "] " + getDescription() +
                fromStringFormatted + toStringFormatted; //+ from.getClass() + to.getClass();
    }
}
