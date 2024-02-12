package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Event extends Task {
    protected LocalDate start;
    protected LocalDate end;

    public Event(String description, String start, String end) {
        super(description);
        this.start = LocalDate.parse(start);
        this.end = LocalDate.parse(end);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " +this.generateStartDateString() + " to: "
                + this.generateEndDateString() + ")";
    }

    @Override
    public String toTaskSaveString() {
        return "E|" + this.getStatusInt() + "|" + this.description + "|" + this.generateStartSaveString()
                + "|" + this.generateEndSaveString();
    }

    private String generateStartDateString() {
        return this.start.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    private String generateEndDateString() {
        return this.end.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    private String generateStartSaveString() {
        return this.start.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    private String generateEndSaveString() {
        return this.end.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}
