import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private final DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private final DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("MMM d yyyy");
    public Event(String d, int s, String st, String et) {
        super(d, s);
        this.startTime = LocalDateTime.parse(st, this.inputFormat);
        this.endTime = LocalDateTime.parse(et, this.inputFormat);
    }

    public String getTime() {
        String st = this.getStartTime();
        String et = this.getEndTime();
        return "\tAny time from " + st + " to " + et;
    }

    public String getStartTime() {
        return this.startTime.format(this.outputFormat);
    }

    public String getEndTime() {
        return this.endTime.format(this.outputFormat);
    }

    //public String getStartTime() {
    //    return "\tstarting: " + this.starttime;
    //}

    //public String getEndTime() {
    //    return "\tending: " + this.endtime;
    //}

    @Override
    public String statusMessage() {
        if (this.getStatus() == 0) {
            return "\tWell done! Event: " + this.getDesc() + " finished.";
        } else {
            return "\tEvent updated. Event: " + this.getDesc() + " marked as incomplete.";
        }
    }

    @Override
    public String saveFormat() {
        return Integer.toString(this.getStatus())
                + "," + this.getDesc()
                + "," + this.startTime.format(this.inputFormat)
                + "," + this.endTime.format(this.inputFormat);
    }

    @Override
    public String toString() {
        if (this.getStatus() == 0) {
            return "[D] [ ] " + this.getDesc() + " (from:" + this.getStartTime() + ", to:" + this.getEndTime() + ")";
        } else {
            return "[D] [X] " + this.getDesc() + " (from:" + this.getStartTime() + ", to:" + this.getEndTime() + ")";
        }
    }
}
