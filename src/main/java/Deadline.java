import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("[yyyy-MM-dd HH:mm][yyyy/MM/dd HH:mm][yyyy MM dd HH:mm][yyyy.MM.dd HH:mm]");
    private LocalDateTime by;

    public Deadline (String description, String by) {
        super(description);
        if (by.length() < 10 || (by.length() > 16 && by.length() != 64)) {
            throw new IllegalArgumentException("Please enter a valid date and time in the format yyyy-MM-dd HH:mm");
        }
        if (by.length() == 10) {
            by += " 00:00";
        }
        this.by = LocalDateTime.parse(by, this.formatter);
    }

    public Deadline (String description, String by, boolean isDone) {
        super(description, isDone);
        if (by.length() < 10 || (by.length() > 16 && by.length() != 64)) {
            throw new IllegalArgumentException("Please enter a valid date and time in the format yyyy-MM-dd HH:mm");
        }
        if (by.length() == 10) {
            by += " 00:00";
        }
        this.by = LocalDateTime.parse(by, this.formatter);
    }

    public String getBy() {
        return this.by.format(this.formatter);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")) + ")";
    }
}