package simpli.tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;
    private static final DateTimeFormatter FORMATTER =DateTimeFormatter.ofPattern("d/M/yyyy HHmm");


    public Event(boolean isDone, String name, LocalDateTime from, LocalDateTime to) {
        super(isDone, name);
        this.from = from;
        this.to = to;
    }

    public String toCsv() {
        return String.format("Event,%s,%s,%s", super.toCsv(),
                this.from.format(FORMATTER), this.to.format(FORMATTER));
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(),
                this.from.format(FORMATTER), this.to.format(FORMATTER));
    }
}
