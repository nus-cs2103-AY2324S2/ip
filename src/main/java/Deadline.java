import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

public class Deadline extends Task {
    protected LocalDateTime by;

    public Deadline (String description, String by) {
        super(description);
        this.by = parseDate(by);
    }

    public Deadline (String description, boolean done, String by) {
        super(description);
        super.updateIsDone(done);
        this.by = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    @Override
    public String getSaveTask() {
        return "D | " + super.getSaveTask() + " | " + by.toString().replace("T", " ");
    }

    @Override
    public String toString() {
        return "[D][" + this.getStatusIcon() + "] " + this.description + " (by: "
                + this.by.format(DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a")) + ")";
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