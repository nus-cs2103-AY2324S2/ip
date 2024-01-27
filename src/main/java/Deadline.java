import java.time.LocalDateTime;

public class Deadline extends Task {
    private LocalDateTime deadline;

    public Deadline(boolean isDone, String description, LocalDateTime deadline) {
        super(isDone, description);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline.format(Duke.outputdtFormatter) + ")";
    }

    @Override
    public String toSave() {
        return "[D]|" + super.toSave() + "|" + deadline.format(Duke.inputdtFormatter);
    }
}
