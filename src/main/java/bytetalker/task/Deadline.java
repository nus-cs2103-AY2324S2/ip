package bytetalker.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Represents the Deadline task that the user wants to store. It contains deadline variable to store deadline time.
 */
public class Deadline extends Task {
    private LocalDateTime deadline;

    public Deadline(String task, LocalDateTime deadline) {
        super(TaskType.DEADLINE, task);
        this.deadline = deadline;
    }

    public Deadline(String task, LocalDateTime deadline, boolean isDone) {
        super(TaskType.DEADLINE, task, isDone);
        this.deadline = deadline;
    }

    /**
     * Creates a string to show information(task type, status, task content, deadline) about the deadline task.
     *
     * @return String that contains information about deadline.
     */
    @Override
    public String toString() {
        return "[" + getTaskType().getIcon() + "]" + "[" + getStatusIcon() + "] " + getTask() + " (by: " + convertDeadlineToString() + ")";
    }

    /**
     * Converts the deadline varialble which is LocalDateTime type to String in set output format(MMM dd yyyy h:mma)
     * to be able to be printed.
     *
     * @return String of deadline variable.
     */
    public String convertDeadlineToString() {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy h:mma", Locale.ENGLISH);
        String formattedDateTime = this.deadline.format(outputFormatter);
        return formattedDateTime;
    }

    public LocalDateTime getDeadline() {
        return this.deadline;
    }

    public void updateDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }
}
