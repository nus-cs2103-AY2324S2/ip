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
     * Return a new deadline task.
     *
     * @param descriptionWithDateTime description with date.
     * @return A new deadline task.
     */
    public DeadlineTask createTask(String descriptionWithDateTime) throws RochinException {
        String[] parts = descriptionWithDateTime.split("/by");
        if (parts.length == 2) {
            String description = parts[0].trim();
            String dateTimeString = parts[1].trim();
            LocalDateTime byDateTime = DateAndTime.parseDateTime(dateTimeString);
            return new DeadlineTask(description, byDateTime);
        } else {
            throw new RochinException("OOPS!!! Please provide both a description and a deadline for a deadline task.");
        }
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
        return "[D]" + super.toString() + " (by: " + byDateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm")) + ")";
    }

    @Override
    public String toFileString() {
        return String.format("%s | %d | %s | %s", getTaskType(), isDone ? 1 : 0, description, DateAndTime.printDate(byDateTime));
    }
}

