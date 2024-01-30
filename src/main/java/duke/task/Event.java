package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    private LocalDateTime start;
    private LocalDateTime end;

    public Event(String task, String start, String end) {
        super(task);
        this.start = setDate(start);
        this.end = setDate(end);
    }

    /**
     * Assigns value of LocalDateTime from String.
     *
     * @param s String specifying date and time in format 'dd-MM-yyyy HH:mm"'.
     * @return LocalDateTime from parsed String s.
     * @throws DateTimeParseException if s is in invalid format or specifies an invalid date time value.
     */
    public LocalDateTime setDate(String s) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        LocalDateTime date = null;
        try {
            date = LocalDateTime.parse(s, formatter);
        } catch (DateTimeParseException e) {
            throw new DateTimeParseException("Invalid start/end date. Specify date in format 'dd-MM-yyyy HH:mm'.", e.getParsedString(), e.getErrorIndex());
        }
        return date;
    }

    /**
     * {@inheritDoc}
     *
     * @return String with type of task, description of event, start and end date time.
     */
    @Override
    public String printTask() {
        return "[E]" + super.printTask() + " (from: " + printDate(this.start) + " to: " + printDate(this.end) + ")";
    }

    @Override
    public String toString() {
        return String.format("E | %s | %s | %s", super.toString(), this.start.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")), this.end.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
    }

    /**
     * Returns date in format 'MMM dd yyyy, HH:mm'.
     *
     * @param date LocalDateTime to be formatted.
     * @return String of formatted localDateTime.
     */
    public String printDate(LocalDateTime date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm");
        return date.format(formatter);
    }
}
