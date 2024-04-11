package task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/** This class represents a deadline where the User can specify when a task would end by*/
public class Deadline extends Task {
    /** The field for when teh task ends by*/
    protected LocalDateTime by;

    /**
     * Constructs a deadline object that takes the task as per the description and when the task
     * ends by
     * @param description
     * @param by
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    public Deadline(String description, Boolean isDone, LocalDateTime by) {
        super(description, isDone);
        this.by = by;
    }
    /**
     * Return the String text that displays the task , when it ends by and with
     * the relevant tag
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")) + ")";
    }


    @Override
    public String toDataFormat() {
        String isDone = this.isDone ? "1 | " : "0 | ";
        return "D | " + isDone + description + " | " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm"));
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof Deadline)) {
            return false;
        }

        Deadline other = (Deadline) obj;
        return
                this.description.equals(other.description) &&
                        this.by.equals(other.by);
    }
}
