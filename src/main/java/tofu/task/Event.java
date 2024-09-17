package tofu.task;

import tofu.TofuException;
import tofu.command.AddCommand;
import tofu.ui.Ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task that occurs over a range of dates.
 * This task type includes two LocalDate variables 'from' and 'to' to represent the start and end dates of the event.
 */
public class Event extends Task {
    private LocalDate from;
    private LocalDate to;

    /**
     * Constructs a new Event task with the specified description, start date, and end date.
     *
     * @param desc The description of the task.
     * @param from The start date of the event in yyyy-MM-dd format.
     * @param to The end date of the event in yyyy-MM-dd format.
     * @throws TofuException If the provided date format is invalid.
     */
    public Event(String desc, String from, String to) throws TofuException {
        super(desc);
        try {
            this.from = LocalDate.parse(from);
            this.to = LocalDate.parse(to);
        } catch (DateTimeParseException ex) {
            throw new TofuException(Ui.invalidDateError());
        }
    }

    /**
     * Constructs a new Event task with the specified description, status, start date, and end date.
     *
     * @param desc The description of the task.
     * @param isDone The status of the task.
     * @param from The start date of the event in yyyy-MM-dd format.
     * @param to The end date of the event in yyyy-MM-dd format.
     * @throws TofuException If the provided date format is invalid.
     */
    public Event(String desc, boolean isDone, String from, String to) throws TofuException {
        super(desc, isDone);
        try {
            this.from = LocalDate.parse(from);
            this.to = LocalDate.parse(to);
        } catch (DateTimeParseException ex) {
            throw new TofuException(Ui.invalidDateError());
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else if (obj instanceof Event) {
            Event event = (Event) obj;
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
