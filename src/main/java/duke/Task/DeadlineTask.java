package duke.Task;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline.
 */
public class DeadlineTask extends Task {

    private LocalTime deadline;

    /**
     * Constructs a DeadlineTask object with the given description and deadline.
     *
     * @param description The description of the task.
     * @param by The deadline of the task.
     */
    public DeadlineTask(String description, LocalTime by) {
        super(description);
        this.deadline = by;
    }

    /**
     * Converts the DeadlineTask object to a string representation for saving to a file.
     *
     * @return The string representation of the DeadlineTask object.
     */
    @Override
    public String toFileString() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + deadline.format(DateTimeFormatter.ofPattern("HHmm"));
    }

    /**
     * Converts the DeadlineTask object to a string representation for displaying to the user.
     *
     * @return The string representation of the DeadlineTask object.
     */
    @Override
    public String toString() {
        return "[D][" + (isDone ? "X" : " ") + "] " + description + " (by: " +deadline + ")";
    }
}
