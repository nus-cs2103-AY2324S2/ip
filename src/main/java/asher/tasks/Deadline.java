package asher.tasks;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a type of task called deadline.
 */
public class Deadline extends Task {
    protected LocalDate dueDate;
    protected LocalTime dueTime;

    /**
     * Constructs a Deadline with the given description and the given due date.
     *
     * @param description The description of the deadline.
     * @param dueDate The end date of the deadline.
     * @param dueTime The end time of the deadline.
     */
    public Deadline(String description, LocalDate dueDate, LocalTime dueTime) {
        super(description);
        this.dueDate = dueDate;
        this.dueTime = dueTime;
    }

    /**
     * Retrieves the due date of the task.
     *
     * @return The due date in LocalDate object.
     */
    public LocalDate getDueDate() {
        return dueDate;
    }

    /**
     * Retrieves the due time of the task.
     *
     * @return The due time in LocalTime object.
     */
    public LocalTime getDueTime() {
        return dueTime;
    }

    @Override
    public String writeToString() {
        String status = isDone ? "1" : "0";
        return "D" + " | " + status + " | " + description + " | " + dueDate + " | " + dueTime;
    }

    @Override
    public String toString() {
        DateTimeFormatter formattingDate = DateTimeFormatter.ofPattern("MMM dd yyyy");
        DateTimeFormatter formattingTime = DateTimeFormatter.ofPattern("hh:mm a");
        String formattedDate = dueDate.format(formattingDate);
        String formattedTime = dueTime.format(formattingTime);
        return " [D]" + super.toString() + " (by: " + formattedDate + "," + " " + formattedTime + ")";
    }
}
