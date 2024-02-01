import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate deadline;

    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = LocalDate.parse(deadline);
    }

    public Deadline(String description, LocalDate deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String saveTask() {
        return "[D]" + super.toString() + " by: " + this.deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " +
                this.deadline.format(DateTimeFormatter.ofPattern("dd MMM yyyy")) + ")";
    }
}
