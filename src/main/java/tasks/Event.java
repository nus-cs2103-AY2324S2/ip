package tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;

public class Event extends Task {

    protected String from;
    protected String to;
    protected Optional<LocalDate> fromDate;
    protected Optional<LocalDateTime> fromDateTime;
    protected Optional<LocalDate> toDate;
    protected Optional<LocalDateTime> toDateTime;

    public Event(String description, String from, String to) {
        super(description);

        this.fromDate = parseDate(from);
        this.fromDateTime = parseDateTime(from);
        // init from string depending on type, else use given by string
        if (fromDate.isPresent() || fromDateTime.isPresent()) {
            this.from = formatFromString(from);
        } else {
            this.from = from;
        }

        this.toDate = parseDate(to);
        this.toDateTime = parseDateTime(to);
        // init from string depending on type, else use given by string
        if (toDate.isPresent() || toDateTime.isPresent()) {
            this.to = formatToString(from);
        } else {
            this.to = to;
        }

    }

    public Event(String completed, String description, String from, String to) {
        super(description, completed.equals("1"));
        this.from = from;
        this.to = to;
    }

    private Optional<LocalDateTime> parseDateTime(String s) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
            return Optional.of(LocalDateTime.parse(s, formatter));
        } catch (DateTimeParseException e) {
            return Optional.empty();
        }
    }

    private Optional<LocalDate> parseDate(String s) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            return Optional.of(LocalDate.parse(s, formatter));
        } catch (DateTimeParseException e) {
            return Optional.empty();
        }
    }

    private String formatFromString(String standard) {
        if (fromDateTime.isPresent()) {
            return fromDateTime.get().format(DateTimeFormatter.ofPattern("d 'of' MMMM yyyy, ha"));
        } else if (fromDate.isPresent()) {
            return fromDate.get().format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        }
        return standard;

    }

    private String formatToString(String standard) {
        if (toDateTime.isPresent()) {
            return toDateTime.get().format(DateTimeFormatter.ofPattern("d 'of' MMMM yyyy, ha"));
        } else if (toDate.isPresent()) {
            return toDate.get().format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        }
        return standard;
    }

    @Override
    public String textFormattedOutput() {
        int intIsDone = isDone ? 1 : 0;
        return String.format("E | %d | %s | %s | %s", intIsDone, description, from, to);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}