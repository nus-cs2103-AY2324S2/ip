package task;

import java.time.LocalDateTime;

public class Event extends Task {
    private final LocalDateTime from;
    private final LocalDateTime to;
    public Event(String msg) {
        super();
        String[] res = msg.split(" ((/from)|(/to)) ");
        this.msg = res[0];
        this.from = this.dateParse(res[1]);
        this.to = this.dateParse(res[2]);
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
