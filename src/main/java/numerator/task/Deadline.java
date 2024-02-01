package numerator.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class Deadline extends numerator.task.Task {
    private final LocalDateTime by;

    public Deadline(String description, String by) throws DateTimeParseException {
        super(description);
        this.by = Task.parseStringToLocalDatetime(by);
    }


    @Override
    public String toString() {
        return String.format(
                "[D][%s] %s (by: %s)",
                this.getStatusIcon(),
                this.description,
                Task.parseLocalDateTimeToString(this.by)
        );
    }

    @Override
    public String getSaveString() {
        return String.format("D | %d | %s | %s",
                this.isDone ? 1 : 0,
                this.description,
                Task.parseLocalDateTimeToString(this.by)
        );
    }
}
