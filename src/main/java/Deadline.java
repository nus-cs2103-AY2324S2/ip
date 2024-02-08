import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    private LocalDateTime time;
    private final static DateTimeFormatter INPUTFORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private final static DateTimeFormatter OUTPUTFORMAT = DateTimeFormatter.ofPattern("MMM d yyyy");
    public Deadline(String d, int s, String t) {
        super(d, s);
        try {
            this.time = LocalDateTime.parse(t, INPUTFORMAT);
        } catch (DateTimeParseException e) {
            System.out.println("Please input the time in yyyy-MM-dd HH:mm format.");
        }
    }

    public static Deadline createDeadline(String description, int status, String time) {
        try {
            LocalDateTime deadline = LocalDateTime.parse(time, INPUTFORMAT);
            return new Deadline(description, status, time);
        } catch (DateTimeParseException e) {
            return null;
            //System.out.println("\tError, please input the time in yyyy-MM-dd HH:mm format.");
        }
    }

    public String getTime() {
        return this.time.format(OUTPUTFORMAT);
    }

    @Override
    public String statusMessage() {
        if (this.getStatus() == 0) {
            return "\tWell done! Deadline: " + this.getDesc() + " finished.";
        } else {
            return "\tDeadline updated. Deadline: " + this.getDesc() + " marked as incomplete.";
        }
    }

    @Override
    public String saveFormat() {
        return Integer.toString(this.getStatus())
                + "," + this.getDesc()
                + "," + this.time.format(INPUTFORMAT);
    }

    @Override
    public String toString() {
        if (this.getStatus() == 0) {
            return "[D] [ ] " + this.getDesc() + " (by: " + this.getTime() + ")";
        } else {
            return "[D] [X] " + this.getDesc() + " (by: " + this.getTime() + ")";
        }
    }
}
