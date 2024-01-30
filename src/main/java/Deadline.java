import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDateTime time;
    private final DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private final DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("MMM d yyyy");
    public Deadline(String d, int s, String t) {
        super(d, s);
        this.time = LocalDateTime.parse(t, this.inputFormat);
    }

    public String getTime() {
        return this.time.format(this.outputFormat);
    }

    @Override
    public String statusMessage() {
        if (this.getStatus() == 0) {
            return "\tWell done! Deadline: " + this.getDesc() + " beaten.";
        } else {
            return "\tDeadline updated. Deadline: " + this.getDesc() + " marked as incomplete.";
        }
    }

    @Override
    public String saveFormat() {
        return Integer.toString(this.getStatus())
                + "," + this.getDesc()
                + "," + this.time.format(this.inputFormat);
    }

    @Override
    public String toString() {
        if (this.getStatus() == 0) {
            return "[D] [ ] " + this.getDesc() + " (by:" + this.getTime() + ")";
        } else {
            return "[D] [X] " + this.getDesc() + " (by:" + this.getTime() + ")";
        }
    }
}
