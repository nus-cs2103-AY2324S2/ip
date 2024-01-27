package duke.task;

import java.time.LocalDateTime;

public class Deadline extends Task {

    private final LocalDateTime dueBy;
    public Deadline(String event, LocalDateTime dueBy) {
        super();
        this.msg = event;
        this.dueBy = dueBy;
    }

    @Override
    public String toString() {
        String is_done = this.done ? "X" : " ";
        return String.format("[D][%s] %s (by: %s)", is_done, this.msg, this.dateFormat(this.dueBy));
    }

}
