package kaiyap;

import java.time.LocalDateTime;

/**
 * Represents a Deadline task in the KaiYap application.
 * A Deadline task is a type of task that has a specific due date and time.
 * This class extends the Task class, inheriting its basic task functionalities
 * and adding a deadline-specific feature.
 */
public class Deadline extends Task {
    private final LocalDateTime deadline;
    /**
     * Constructs a new Deadline task with the specified description, additional input, and deadline date and time.
     *
     * @param listItem  The description of the Deadline task.
     * @param inputItem Additional information for the task, if any.
     * @param deadline  The LocalDateTime object representing the deadline of the task.
     */
    public Deadline(String listItem, String inputItem, LocalDateTime deadline) {
        super(listItem, inputItem);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]"
                + (this.isCompleted ? "[X] " : "[ ] ")
                + this.listItem
                + " (by: "
                + this.dtf.format(this.deadline)
                + ")";
    }
}
