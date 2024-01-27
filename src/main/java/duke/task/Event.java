package duke.task;

import java.time.LocalDateTime;

public class Event extends Task {
    private final LocalDateTime from;
    private final LocalDateTime to;
    public Event(String msg, LocalDateTime from, LocalDateTime to) {
        super();
        this.msg = msg;
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        String is_done = this.done ? "X" : " ";
        return String.format(
                "[E][%s] %s (from: %s to: %s)",
                is_done,
                this.msg,
                this.dateFormat(this.from),
                this.dateFormat(this.to)
        );
    }
}
