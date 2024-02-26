package duke.tasks;

import java.time.LocalDate;

/**
 * The DeadlineTask class represents a task with a deadline.
 * It extends the Task class and adds a deadline attribute.
 * <p>
 * A deadline task includes a description and a deadline date.
 * </p>
 *
 * @author Justin Leng Chern Harn
 * @version 1.0
 * @see duke.tasks.Task
 */
public class DeadlineTask extends Task {
    protected LocalDate doneBy;

    /**
     * Constructs a DeadlineTask with the specified description and deadline date.
     *
     * @param description the description of the task.
     * @param doneBy the deadline date of the task.
     */
    public DeadlineTask(String description, LocalDate doneBy) {
        super(description);
        this.doneBy = doneBy;
    }

    /**
     * Returns the deadline date of the task.
     *
     * @return the deadline date of the task.
     */
    public LocalDate getDoneBy() {
        return this.doneBy;
    }

    @Override
    public String toString() {
        return "D" + " | " + super.toString() + " | " + "(by: " + getDoneBy() + ")";
    }

}
