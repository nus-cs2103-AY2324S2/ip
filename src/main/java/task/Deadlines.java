package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class Deadlines extends Task {
    private final LocalDateTime by;

    public Deadlines(String description, String by) throws DateTimeParseException {
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
