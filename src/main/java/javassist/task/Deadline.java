package javassist.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a deadline object.
 */
public class Deadline extends Task {
    private LocalDateTime dueDate;

    /**
     * Constructs a Deadline object.
     *
     * @param task Description of task.
     * @param date Deadline of task.
     */
    public Deadline(String task, String date) {
        super(task);
        this.dueDate = setDate(date);
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
            throw new DateTimeParseException("Invalid due date. Specify date in format 'dd-MM-yyyy HH:mm'.",
                    e.getParsedString(), e.getErrorIndex());
        }
        return date;
    }

    /**
     * {@inheritDoc}
     *
     * @return String with type of task, description of deadline, and time to do by.
     */
    @Override
    public String printTask() {
        return "[D]" + super.printTask() + " (by: " + printDate() + ")";
    }

    @Override
    public String toString() {
        return String.format("D | %s | %s", super.toString(),
                this.dueDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
    }

    /**
     * Returns date in format 'MMM dd yyyy, HH:mm'.
     *
     * @return String of formatted localDateTime.
     */
    private String printDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm");
        return this.dueDate.format(formatter);
    }
}
