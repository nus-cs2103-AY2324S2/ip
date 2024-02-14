package duke;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Handles deadline tasks
 */
public class Deadline extends Task {
    /**
     * The deadline of the task
     */
    LocalDateTime deadline;

    /**
     * Creates a Deadline object to handle a deadline task
     *
     * @param name the name of the task
     * @param deadline the deadline of the task
     * @param isDone whether the task is done
     */
    public Deadline(String name, LocalDateTime deadline, boolean isDone) {
        this.name = name;
        this.deadline = deadline;
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        String str = "";
        str = String.format("[D]%s (by: %s)", super.toString(), deadline.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm")));
        return str;
    }

    @Override
    public String convertToText() {
        String str = "";
        str = String.format("%s deadline %s /by %s", isDone, name, deadline);
        return str;
    }

    /**
     * Gets the deadline
     * @return the deadline
     */
    public LocalDateTime getDeadline() {
        return deadline;
    }
}
