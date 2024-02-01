package bozo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime by;
    protected static DateTimeFormatter input = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    protected static DateTimeFormatter output = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");

    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDateTime.parse(by, input);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), by.format(output));
    }

    @Override
    public String save() {
        return "D " + super.save() + String.format(" | %s", by.format(input));
    }
}
