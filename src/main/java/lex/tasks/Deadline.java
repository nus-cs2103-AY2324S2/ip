package lex.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a deadline task.
 */
public class Deadline extends Task {
    protected LocalDate end;

    /**
     * Constructor for the Deadline class.
     *
     * @param title The title of the deadline.
     * @param end   The end date of the deadline.
     */
    public Deadline(@JsonProperty("title") String title, @JsonProperty("end") LocalDate end) {
        super(title);
        this.end = end;
    }

    /**
     * Gets the end date of the deadline.
     *
     * @return The end date of the deadline.
     */
    public LocalDate getEnd() {
        return end;
    }

    /**
     * Sets the end date of the deadline.
     *
     * @param end The end date of the deadline.
     */
    public void setEnd(LocalDate end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(),
                end.format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
    }
}
