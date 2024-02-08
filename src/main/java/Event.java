import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private final static DateTimeFormatter INPUTFORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private final static DateTimeFormatter OUTPUTFORMAT = DateTimeFormatter.ofPattern("MMM d yyyy");
    public Event(String d, int s, String st, String et) {
        super(d, s);
        this.startTime = LocalDateTime.parse(st, INPUTFORMAT);
        this.endTime = LocalDateTime.parse(et, INPUTFORMAT);
    }

    public static Event createEvent(String description, int status, String start, String end) {
        try {
            LocalDateTime starttime = LocalDateTime.parse(start, INPUTFORMAT);
            LocalDateTime endtime = LocalDateTime.parse(end, INPUTFORMAT);
            return new Event(description, status, start, end);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    public String getTime() {
        String st = this.getStartTime();
        String et = this.getEndTime();
        return "\tAny time from " + st + " to " + et;
    }

    public String getStartTime() {
        return this.startTime.format(OUTPUTFORMAT);
    }

    public String getEndTime() {
        return this.endTime.format(OUTPUTFORMAT);
    }


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
                + "," + this.startTime.format(INPUTFORMAT)
                + "," + this.endTime.format(INPUTFORMAT);
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
