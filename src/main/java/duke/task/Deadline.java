package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline-type task. It includes a description and a due date.
 * This class extends the Task class, adding functionality specific to tasks that have a deadline.
 */
public class Deadline extends Task {

    private String due;

    /**
     * Constructs a new Deadline instance with a specified description and due date.
     * 
     * @param description The text description of the deadline task.
     * @param due The due date of the task.
     */
    public Deadline(String description, LocalDate due) {
        super(description);
        this.due = due.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    
    /**
     * Generates a string representation of the deadline task, including its type indicator "[D]",
     * completion status, description, and due date.
     * 
     * @return A string that represents the deadline task, showing its status, description, and due date.
     */
    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + this.due + ")";
    }
}
