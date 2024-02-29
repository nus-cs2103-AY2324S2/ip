package SamuelBot;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The Deadline class represents a task with a deadline.
 */
public class Deadline extends Task {
    private LocalDate byDate;
    private String by;

    /**
     * Constructs a new Deadline object with the given description and deadline.
     *
     * @param description The description of the deadline task.
     * @param by The deadline of the task in the format "yyyy-MM-dd".
     */
    public Deadline(String description, String by) {
        super(description);
        String[] parts = by.split("\\s+");
        String datePart = parts[0];
        this.byDate = LocalDate.parse(datePart, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    /**
     * Gets the deadline of the task.
     *
     * @return The deadline of the task in the format "yyyy-MM-dd".
     */
    public String getBy() {
        return by;
    }

    /**
     * Gets the formatted deadline date.
     *
     * @return The formatted deadline date in the format "MMM dd yyyy".
     */
    public String getByDateFormatted() {
        return byDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    /**
     * Gets the type of the task.
     *
     * @return The type of the task, which is "D" for deadline.
     */
    @Override
    public String getTaskType() {
        return "D";
    }

    /**
     * Converts the deadline task to a string format for display.
     *
     * @return A string representation of the deadline task.
     */
    @Override
    public String toString() {
        return super.toString() + " (by: " + getByDateFormatted() + ")";
    }

    /**
     * Converts the deadline task to a string format for saving to a file.
     *
     * @return A string representation of the deadline task in file format.
     */
    @Override
    public String toFileString() {
        return "D | " + (isDone() ? "1" : "0") + " | " + description + " | " + byDate;
    }

    /**
     * Gets the description of the deadline task.
     *
     * @return The description of the deadline task.
     */
    @Override
    public String getDescription(){
        return description;
    }

    /**
     * Checks if the deadline task is done.
     *
     * @return true if the deadline task is done, false otherwise.
     */
    @Override
    public boolean isDone(){
        return isDone;
    }
}
