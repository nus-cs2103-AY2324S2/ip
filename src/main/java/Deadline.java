import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected String by;
    protected LocalDateTime dateTime;

    // code snippet below inspired from:
    // https://stackoverflow.com/questions/44600420/datetimeformatter-accepting-multiple-dates-and-converting-to-one-java-time-libr
    protected DateTimeFormatter parser = DateTimeFormatter.ofPattern("[yyyy-MM-dd HHmm][MMM dd yyyy HHmm]");

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        this.dateTime = LocalDateTime.parse(by, parser);
    }

    public Deadline(String status, String description, String by) {
        super(status.equals("1"), description);
        this.by = by;
        this.dateTime = LocalDateTime.parse(by, parser);
    }

    public String dateTimeFormat() {
        return this.dateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm"));
    }

    @Override
    public String toFile() {
        return "D | " + (isDone ? "1" : "0")
                + " | " + description + " | " + this.dateTimeFormat();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString()
                + " (by: " + this.dateTimeFormat() + ")";
    }
}