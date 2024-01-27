/**
 * Represents a deadline task. It extends the Task class and adds a deadline time.
 */
public class Deadline extends Task {
    private final String by; // Deadline time for the task

    /**
     * Constructs a Deadline task with a description and a deadline time.
     *
     * @param description A string describing the deadline task.
     * @param by A string representing the time by which the task should be completed.
     */
    public Deadline(String description, String by) {
        super(description); // Calls the constructor of the superclass Task
        this.by = by;       // Sets the deadline time for this task
    }

    /**
     * Returns a string representation of the deadline task, including its type,
     * description, and deadline time.
     *
     * @return A formatted string with the type of task, its description, and the deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
