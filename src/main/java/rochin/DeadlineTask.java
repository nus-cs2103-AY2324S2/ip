package rochin;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represent a Deadline task.
 */
class DeadlineTask extends Task {

    protected LocalDateTime byDateTime;

    public DeadlineTask(String description, LocalDateTime byDateTime) {
        super(description);
        this.byDateTime = byDateTime;
    }


    /**
     * Return a string representation of the task type.
     *
     * @return A string representation of the task type.
     */
    @Override
    public String getTaskType() {
        return "D";
    }

    /**
     * Return a string representation of the task.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        return "[D]"
                + super.toString()
                + " (by: "
                + byDateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm")) + ")";
    }

    /**
     * Return a string representation of the task to be stored in the file.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toFileString() {
        return String.format(
                "%s | %d | %s | %s",
                getTaskType(),
                isDone ? 1 : 0,
                description,
                DateAndTime.printDate(byDateTime));
    }
}

