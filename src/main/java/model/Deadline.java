package model;

import exceptions.BadDateException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDateTime by;
    protected String byString;

    public Deadline(String description, String by) {
        super(description);
        this.byString = by;
        try {
            LocalDateTime moment = super.parseDateTime(by);
            this.by = moment;
        } catch (Exception e) {
            String expectedDateFormat = "YYYY-MM-DD or YYYY/MM/DD or DD-MM-YYYY or DD/MM/YYYY";
            String example = "2024-12-14";
            throw new BadDateException(e.getMessage(), expectedDateFormat, example, by);
        }
    }

    public Deadline(String description, String by, String tag) {
        super(description, tag);
        this.byString = by;
        try {
            LocalDateTime moment = super.parseDateTime(by);
            this.by = moment;
        } catch (Exception e) {
            String expectedDateFormat = "YYYY-MM-DD or YYYY/MM/DD or DD-MM-YYYY or DD/MM/YYYY";
            String example = "2024-12-14";
            throw new BadDateException(e.getMessage(), expectedDateFormat, example, by);
        }

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
        return "D | " + Integer.toString(super.isDone ? 1 : 0) + " | " + super.description + " | " + byString + " | " + super.getTag();
    }

}
