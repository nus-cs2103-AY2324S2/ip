package duke.Task;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DeadlineTask extends Task {

    private LocalTime deadline;

    public DeadlineTask(String description, LocalTime by) {
        super(description);
        this.deadline = by;
    }

    @Override
    public String toFileString() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + deadline.format(DateTimeFormatter.ofPattern("HHmm"));
    }

    @Override
    public String toString() {
        return "[D][" + (isDone ? "X" : " ") + "] " + description + " (by: " +deadline + ")";
    }
}
