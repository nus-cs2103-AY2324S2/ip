package entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The Deadline class which extends the Task class and includes a deadline represented by a LocalDateTime object.
 */
public class Deadline extends Task {
    protected LocalDateTime dateTime;

    /**
     * Constructor for Deadline class
     * @param title
     * @param dateTime
     */
    public Deadline(String title, LocalDateTime dateTime) {
        super(title);
        this.dateTime = dateTime;
    }

    public Deadline(String title, boolean isMarked, LocalDateTime dateTime) {
        super(title);
        this.isMarked = isMarked;
        this.dateTime = dateTime;
    }

    /**
     * Helper function to generate a string representation of the Deadline object for saving purposes.
     *
     * @return A string representation of the Deadline object for saving to a file.
     */
    @Override
    public String save() {
        if (this.getMarked()) {
            return "D|Done|" + this.title + "|" + this.dateTime;
        } else {
            return "D|Not Done|" + this.title + "|" + this.dateTime;
        }
    }
    @Override
    public String toString() {

        if (this.getMarked()) {
            return "[D][X] " + this.title + "by " + this.dateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } else {
            return "[D][ ] " + this.title + "by " + this.dateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        }
    }

    @Override
    public LocalDateTime getDate() {
        return this.dateTime;
    }
}
