import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * Creates a Deadline task with description and deadline.
     *
     * @param description The description of the Deadline task.
     * @param by The deadline of the task.
     */
    public Deadline(String description, boolean isDone, LocalDateTime by) {
        super(description, isDone);
        this.by = by;
    }

    @Override
    public String toString() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("d MMM yyyy HH:mm");
        return "D" + super.toString() + " | " + this.by.format(dateTimeFormatter);
    }
}
