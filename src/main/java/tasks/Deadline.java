package tasks;

import java.time.LocalDate;

/**
 * The `Deadline` class extends the `Task` class and represents a task with a specific deadline.
 * It includes methods to get the type icon, description with the deadline, and the command format for saving to a file.
 */
public class Deadline extends Task {

    private LocalDate deadline;

    /**
     * Constructs a `Deadline` object with the specified description and deadline date.
     *
     * @param description The description of the deadline task.
     * @param date        The deadline date of the task.
     */
    public Deadline(String description, LocalDate date) {
        super(description);
        this.deadline = date;
    }

    /**
     * Gets the type icon for the deadline task, which is "D".
     *
     * @return The type icon for the deadline task.
     */
    @Override
    public String getTypeIcon() {
        return "D";
    }

    /**
     * Gets the description of the deadline task, including the deadline date.
     *
     * @return The description of the deadline task.
     */
    @Override
    public String toString() {
        return String.format("%s (by: %s)", this.description, this.deadline);
    }

    /**
     * Gets the command format for saving the deadline task to a file.
     *
     * @return The command format for saving the deadline task to a file.
     */
    public String getCommand() {
        return String.format("deadline %s /by %s\n%b\n", this.description, this.deadline, this.isDone);
    }
}

