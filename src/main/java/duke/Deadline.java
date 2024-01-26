package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDateTime byDate;

    public Deadline(String description, String byDate) {
        super(description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        this.byDate = LocalDateTime.parse(byDate, formatter);
    }

    @Override
    public String toFileLine() {
        String fileLine = super.toFileLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return String.format("D | %s | %s", fileLine.substring(5), this.byDate.format(formatter));
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy HH:mm");
        return String.format("[D]%s (by: %s)", super.toString(), this.byDate.format(formatter));
    }
}