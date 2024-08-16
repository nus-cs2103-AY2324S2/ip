package hammy.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The DeadlineTask class represents a task with a deadline.
 * It is a subclass of the Task class.
 */
public class DeadlineTask extends Task {

    /** The deadline of the task. */
    private LocalDate deadline;

    /**
     * Constructs a DeadlineTask object with the given description and deadline.
     * The task is initialized as not done.
     *
     * @param description the description of the task
     * @param deadline    the deadline of the task
     */
    public DeadlineTask(String description, LocalDate deadline) {
        super(description);
        this.deadline = deadline;
    }

    /**
     * Constructs a DeadlineTask object with the given description, deadline, and status.
     *
     * @param description the description of the task
     * @param deadline    the deadline of the task
     * @param isDone      the status of the task, true if it is done, false otherwise
     */
    public DeadlineTask(String description, LocalDate deadline, boolean isDone) {
        super(description, isDone);
        this.deadline = deadline;
    }

    /**
     * Returns a string representation of the DeadlineTask.
     * The string contains the status (done or not done), description, and deadline of the task.
     *
     * @return a string representation of the DeadlineTask
     */
    @Override
    public String toString() {
        LocalDate tempDeadline = deadline.plusDays(0);
        return "[D]" + super.toString() + " (by: " + tempDeadline.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
