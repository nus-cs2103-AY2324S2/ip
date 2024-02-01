import java.time.LocalTime;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

public class Event extends Task {
    protected LocalDateTime from;
    protected LocalTime to;

    public Event (String description, String from, String to) throws IllegalArgumentException {
        super(description);
        this.from = parseDate(from);
        this.to = LocalTime.parse(to, DateTimeFormatter.ofPattern("HHmm"));
    }

    public Event (String description, boolean done, String from, String to) {
        super(description);
        super.updateIsDone(done);
        this.from = LocalDateTime.parse(from, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        this.to = LocalTime.parse(to, DateTimeFormatter.ofPattern("HH:mm"));
    }

    @Override
    public String getSaveTask() {
        return "E | " + super.getSaveTask() + " | "
                + from.toString().replace("T", " ") + " | " + to;
    }

    @Override
    public String toString() {
        return "[E][" + this.getStatusIcon() + "] "
                + this.description + " (from: "
                + this.from.format(DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a")) + " to: "
                + this.to.format(DateTimeFormatter.ofPattern("hh:mm a")) + ")";
    }

    private LocalDateTime parseDate(String dateTime) {
        List<String> dateCombi = Arrays.asList("dd-MM-yyyy ", "MM-dd-yyyy ", "yyyy-dd-MM ", "yyyy-MM-dd ");
        List<String> timeCombi = Arrays.asList("HH:mm", "HHmm", "hh:mm a");

        for (String d : dateCombi) {
            for (String t : timeCombi) {
                try {
                    return LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern(d + t));
                } catch (DateTimeParseException dt) { }
            }
        }

        return null;
    }
}