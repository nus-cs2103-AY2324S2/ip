package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDateTime by;
    protected String byString;

    public Deadline(String description, String by) {
        super(description);
        LocalDateTime moment = super.parseDateTime(by);
        this.by = moment;
        this.byString = by;
    }


    /**
     * {inheritDoc}
     *
     * @return String representation of the deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm")) + ")";
    }

    /**
     * Used to obtain the string in the format that the task would be saved in.
     * @return String representation of the deadline that is savable.
     */
    public String fileSavingString() {
        return "D | " + Integer.toString(super.isDone ? 1 : 0) + " | " + super.description + " | " + byString;
    }

}
