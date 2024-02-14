package osiris.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * The DeadlineTask class represents a task with a deadline.
 * It extends the Task class.
 */
public class DeadlineTask extends Task {

    /** Deadline Date Time of Task. */
    private final LocalDate deadline;

    /**
     * Constructs a DeadlineTask object with the given task name and deadline.
     *
     * @param taskName The name of the task.
     * @param deadline The deadline of the task.
     */
    public DeadlineTask(String taskName, LocalDate deadline) {
        super(taskName);
        this.deadline = deadline;
    }

    /**
     * Constructs a DeadlineTask object with the given task name, completion status, and deadline.
     *
     * @param taskName    The name of the task.
     * @param isCompleted The completion status of the task.
     * @param deadline    The deadline of the task.
     */
    public DeadlineTask(String taskName, boolean isCompleted, LocalDate deadline) {
        super(taskName, isCompleted);
        this.deadline = deadline;
    }

    /**
     * Returns start Local Date Time of Task
     *
     * @return LocalDateTime    The start time of the task.
     */
    @Override
    public LocalDateTime getStartDateTime() {
        return LocalDateTime.of(deadline, LocalTime.of(0, 0));
    }

    /**
     * Retrieves the deadline of the task as a string.
     *
     * @return The deadline of the task formatted as "MMM d yyyy".
     */
    public String getDeadlineStr() {
        return deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * Retrieves the string representation of the DeadlineTask for storage purposes.
     *
     * @return The string representation of the DeadlineTask.
     */
    @Override
    public String getStringStorageRepresentation() {
        return String.format("D | %s | %s", super.getStringStorageRepresentation(), this.getDeadlineStr());
    }

    /**
     * Retrieves the string representation of the DeadlineTask for display purposes.
     *
     * @return The string representation of the DeadlineTask.
     */
    @Override
    public String toString() {
        return String.format("[D] %s (by: %s)", super.toString(), this.getDeadlineStr());
    }
}
