/**
 * This class represents a Deadline task
 * It extends the Task class, adding a due date to the task
 */
public class Deadline extends Task {
    protected String dueDate;

    /**
     * Constructs a new Deadline task with a specified description and due date
     *
     * @param description The description of the task.
     * @param dueDate     The due date of the task.
     */
    public Deadline(String description, String dueDate) {
        super(description);
        this.dueDate = dueDate;
    }

    /**
     * Returns a string representation of the Deadline task.
     * The string includes the task type (D for Deadline), the task description, and
     * the due date.
     *
     * @return A string representation of the task
     */
    @Override
    public String toString() {
        return "D" + super.toString() + " | by: " + this.dueDate;
    }
}