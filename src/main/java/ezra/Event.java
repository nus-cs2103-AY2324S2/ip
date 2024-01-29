package ezra;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    protected LocalDateTime start;
    protected LocalDateTime end;
    protected String startInput;
    protected String endInput;

    public Event(String description, String start, String end) throws DateTimeParseException {
        super(description);
        this.start = LocalDateTime.parse(start, DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
        this.end = LocalDateTime.parse(end, DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
        this.startInput = start;
        this.endInput = end;
    }

    @Override
    public String toString() {
        String startString = this.start.format(DateTimeFormatter.ofPattern("dd MMM yyyy hh:mm a"));
        String endString = this.end.format(DateTimeFormatter.ofPattern("dd MMM yyyy hh:mm a"));
        return String.format(
                "[E][%s] %s (from: %s | to: %s)",
                this.getStatusIcon(),
                this.description,
                startString,
                endString
        );
    }

    public String toString2() {
        return String.format("E | %d | %s | %s | %s", this.isDone ? 1 : 0,
                this.description,
                this.startInput,
                this.endInput
        );
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Event e)) {
            return false;
        }
        return this.description.equals(e.description) &&
                this.startInput.equals(e.startInput) &&
                this.endInput.equals(e.endInput);
    }
}
