package task;

import java.time.LocalDateTime;

public class Events extends Task {
    private final LocalDateTime from;
    private final LocalDateTime to;

    public Events(String description, String from, String to) {
        super(description);
        this.from = Task.parseStringToLocalDatetime(from);
        this.to = Task.parseStringToLocalDatetime(to);
    }


    @Override
    public String toString() {
        return String.format(
                "[E][%s] %s (from: %s to: %s)",
                this.getStatusIcon(),
                this.description,
                Task.parseLocalDateTimeToString(this.from),
                Task.parseLocalDateTimeToString(this.to)
        );
    }

    @Override
    public String getSaveString() {
        return String.format(
                "E | %d | %s | %s | %s",
                this.isDone ? 1 : 0,
                this.description,
                Task.parseLocalDateTimeToString(this.from),
                Task.parseLocalDateTimeToString(this.to)
        );
    }
}
