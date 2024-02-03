package dylanbot;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DeadlineTask extends Task {
    public LocalDateTime deadline;
    DateTimeFormatter printFormat  = DateTimeFormatter.ofPattern("d MMM yyyy HH:mm:ss");
    public DeadlineTask(String desc, LocalDateTime deadline) {
        super("D", desc);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        String res = "";
        try {
            res = "[" + this.getType() + "] "
                    + (this.isCompleted() ? "[X]" : "[ ]")
                    + " " + this.getDesc()
                    + " (by: " + deadline.format(printFormat) + ")";
        } catch (NullPointerException e) {
        }
        return res;
    }
}
