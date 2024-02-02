import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    private LocalDateTime by;
    private static DateTimeFormatter ioFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    private static DateTimeFormatter displayFormatter = DateTimeFormatter.ofPattern("dd LLL yyyy HHmm");

    public Deadline(String description, String by, boolean isDone) throws DateTimeParseException {
        super(description, isDone);
        this.by = LocalDateTime.parse(by, ioFormatter);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(),
                this.by.format(displayFormatter));
    }

    @Override
    public String formatData() {
        return "D" + " | " + this.formatTask() + " | " + this.by.format(ioFormatter);
    }

    public static void setDisplayFormatter(String pattern) {
        Deadline.displayFormatter = DateTimeFormatter.ofPattern(pattern);
    }
}
