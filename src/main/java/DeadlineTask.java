import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DeadlineTask extends Task {
    public LocalDateTime deadline;
    DateTimeFormatter printFormat  = DateTimeFormatter.ofPattern("d MMM yyyy HH:mm:ss");
    public DeadlineTask(String type, String desc, LocalDateTime deadline) {
        super(type, desc);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        String res = "";
        try {
            res = "[" + type + "] "
                    + (completed ? "[X]" : "[ ]")
                    + " " + desc
                    + " (by: " + deadline.format(printFormat) + ")";
        } catch (NullPointerException e) {
        }
        return res;
    }
}
