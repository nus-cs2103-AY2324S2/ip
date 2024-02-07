package duke.task;

import duke.utils.DateTime;

/**
 * The Deadline class represents a task with keyword 'deadline' in the Duke chatbot.
 * It extends the Task class with additional due date information
 */
public class Deadline extends Task {
    protected DateTime by;

    /**
     * Constructs a Deadline task with the given description and deadline.
     *
     * @param description The description of the task.
     * @param by The deadline of the task in string format.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = new DateTime(by);
    }

    /**
     * Generates the formatted content of the Deadline task for storage in a file.
     *
     * @return The formatted content of the Deadline task.
     */
    @Override
    public String writeContent() {
        return "D |" + (this.isDone ? " 1 | " : " 0 | ") + this.getDescription()
                    + " | " + this.by;
    }

    /**
     * Generates a string representation of the Deadline task to display.
     *
     * @return The string representation of the Deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
