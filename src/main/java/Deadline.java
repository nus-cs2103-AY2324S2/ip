import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;

public class Deadline extends Task {

    protected Optional<LocalDateTime> byDateTime;
    protected Optional<LocalDate> byDate;
    protected String by;

    public Deadline(String description, String by) {
        super(description);

        this.byDateTime = parseDateTime(by);
        this.byDate = parseDate(by);
        // init by string depending on type, else use given by string
        if (byDateTime.isPresent() || byDate.isPresent()) {
            this.by = formatByString(by);
        }
    }

    public Deadline(String completed, String description, String by) {
        super(description, completed.equals("1"));
        this.by = by;
    }

    private Optional<LocalDateTime> parseDateTime(String by) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
            return Optional.of(LocalDateTime.parse(by, formatter));
        } catch (DateTimeParseException e) {
            return Optional.empty();
        }
    }

    private Optional<LocalDate> parseDate(String by) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            return Optional.of(LocalDate.parse(by, formatter));
        } catch (DateTimeParseException e) {
            return Optional.empty();
        }
    }

    private String formatByString(String by) {
        if (byDateTime.isPresent()) {
            return byDateTime.get().format(DateTimeFormatter.ofPattern("d 'of' MMMM yyyy, ha"));
        } else if (byDate.isPresent()) {
            return byDate.get().format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        }
        return by;
    }


    @Override
    public String textFormattedOutput() {
        int intIsDone = isDone ? 1 : 0;
        return String.format("D | %d | %s | %s", intIsDone, description, by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}