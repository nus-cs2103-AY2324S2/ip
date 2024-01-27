
import java.time.LocalDateTime;
public class Deadline extends Task {
    private LocalDateTime deadline;
    public Deadline(String listItem, String inputItem, LocalDateTime deadline) {
        super(listItem, inputItem);
        this.deadline = deadline;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]" +
                (this.taskDone ? "[X] " : "[ ] ") +
                this.listItem +
                " (by: " +
                this.deadline + ")"
                ;
    }
}
