import java.time.LocalDate;
import java.time.LocalDateTime;

public class Deadline extends Task {

    private LocalDateTime deadline;
    public Deadline(String description, LocalDateTime deadline) {
        super(description);
        this.deadline = deadline;
    }
    @Override
    public String toString() {
        return String.format("[D][%s] %s (by: %s)", getStatusIcon(), this.description, this.deadline.format(dateFormat));
    }
    @Override
    public String getSaveLine() {
        return "D " + (this.isDone ? "1 " : "0 ") + this.description + " /by " + this.deadline.toString() + "\n";
    }
}
