package duke.tasks;

import java.time.LocalDateTime;

public class DeadlineTask extends Task {
    private LocalDateTime end;
    public DeadlineTask(String desc, LocalDateTime end) {
        super(desc);;
        this.end = end;
    }

    public DeadlineTask(String desc, String isDone, LocalDateTime end) {
        super(desc, isDone);
        this.end = end;
    }

    public String getStatusIcon() {
        return (this.isDone() ? "[D][X] " : "[D][ ] ");
    }

    public String toString() {
        return this.getStatusIcon() + this.getDesc() + " (by: " +
                Task.toStringDateTime(this.end) + ")";
    }
    public String save() {
        String isDone = this.isDone() ? "1" : "0";
        return "D," + isDone + "," + this.getDesc() + "," + this.end;
    }
}
