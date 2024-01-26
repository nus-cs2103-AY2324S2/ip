package task;

import java.time.LocalDateTime;

public class Deadline extends Task {

    private final LocalDateTime dueBy;
    public Deadline(String msg) {
        super();
        String[] res = msg.split(" /by ");
        this.msg = res[0];
        this.dueBy = this.dateParse(res[1]);
    }

    @Override
    public String toString() {
        String is_done = this.done ? "X" : " ";
        return String.format("[D][%s] %s (by: %s)", is_done, this.msg, this.dateFormat(this.dueBy));
    }

}
