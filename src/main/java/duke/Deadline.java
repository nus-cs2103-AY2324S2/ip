package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a "Deadline" task in the Duke application.
 * A deadline task includes a description, a due date, and the original input.
 */
public class Deadline extends Task {
    private String by; // Formatted due date string

    /**
     * Constructs a new Deadline task with the specified description, due date, and
     * input.
     *
     * @param description The task's description.
     * @param dueDate     The due date of the task.
     * @param input       The original input string that created the task.
     */
    Deadline(String description, LocalDate dueDate, String input) {
        super(description, input);
        this.by = dueDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * Returns a string representation of the Deadline task,
     * including its description and due date.
     *
     * @return A string that represents the Deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }

    /**
     * Marks the Deadline task as done and prints a confirmation message.
     */
    @Override
    public String markComplete() {
        super.setComplete();
        return "\tNice! I've marked this task as done:\n\t" + this.toString();
    }

    /**
     * Marks the Deadline task as not done and prints a confirmation message.
     */
    @Override
    public String unmarkComplete() {
        super.setIncomplete();
        return "\tOK, I've marked this task as not done yet:\n\t" + this.toString();
    }

    /**
     * Returns a string format of the Deadline task for file storage,
     * including its completion status and input.
     *
     * @return A string representation of the Deadline task for file storage.
     */
    @Override
    public String toFileFormat() {
        return String.format("Deadline | %s | %s", super.statusNumber, super.input);
    }
}
