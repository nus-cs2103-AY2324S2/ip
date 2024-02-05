import tasks.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.Serializable;

public class Deadline extends Task implements Serializable {
    private LocalDateTime deadline;
    private static final long serialVersionUID = 3L;

    Deadline(String description, LocalDateTime deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + "[" + (isDone ? "X" : " ") + "] " + description + "(by: "
                + deadline.format(DateTimeFormatter.ofPattern("HH:mm MMM dd YYYY")) + ")";
    }
}
