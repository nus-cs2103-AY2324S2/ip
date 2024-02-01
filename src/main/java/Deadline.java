import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

public class Deadline extends Task {
    private LocalDateTime by;
    private static final List<DateTimeFormatter> DFORMATTERS = Arrays.asList(
            DateTimeFormatter.ofPattern("dd-MM-yyyy"),
            DateTimeFormatter.ofPattern("d-MM-yyyy"),
            DateTimeFormatter.ofPattern("dd-M-yyyy"),
            DateTimeFormatter.ofPattern("d-M-yyyy"),
            DateTimeFormatter.ofPattern("dd-MM-yyyy"),
            DateTimeFormatter.ofPattern("d-MM-yyyy"),
            DateTimeFormatter.ofPattern("dd-M-yyyy"),
            DateTimeFormatter.ofPattern("d-M-yyyy"),

            DateTimeFormatter.ofPattern("dd/MM/yyyy"),
            DateTimeFormatter.ofPattern("d/MM/yyyy"),
            DateTimeFormatter.ofPattern("dd/M/yyyy"),
            DateTimeFormatter.ofPattern("d/M/yyyy"),
            DateTimeFormatter.ofPattern("dd/MM/yyyy"),
            DateTimeFormatter.ofPattern("d/MM/yyyy"),
            DateTimeFormatter.ofPattern("dd/M/yyyy"),
            DateTimeFormatter.ofPattern("d/M/yyyy")
            );

    private static final List<DateTimeFormatter> DTFORMATTERS = Arrays.asList(
            DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"),
            DateTimeFormatter.ofPattern("d-MM-yyyy HHmm"),
            DateTimeFormatter.ofPattern("dd-M-yyyy HHmm"),
            DateTimeFormatter.ofPattern("d-M-yyyy HHmm"),
            DateTimeFormatter.ofPattern("dd-MM-yyyy Hmm"),
            DateTimeFormatter.ofPattern("d-MM-yyyy Hmm"),
            DateTimeFormatter.ofPattern("dd-M-yyyy Hmm"),
            DateTimeFormatter.ofPattern("d-M-yyyy Hmm"),

            DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"),
            DateTimeFormatter.ofPattern("d/MM/yyyy HHmm"),
            DateTimeFormatter.ofPattern("dd/M/yyyy HHmm"),
            DateTimeFormatter.ofPattern("d/M/yyyy HHmm"),
            DateTimeFormatter.ofPattern("dd/MM/yyyy Hmm"),
            DateTimeFormatter.ofPattern("d/MM/yyyy Hmm"),
            DateTimeFormatter.ofPattern("dd/M/yyyy Hmm"),
            DateTimeFormatter.ofPattern("d/M/yyyy Hmm")
    );

    public Deadline(String description, String by) throws DukeException {
        super(description);
        this.by = parseDateTime(by);
        if (this.by == null) {
            throw new DukeException("Invalid date time format. Please use a recognized format.");
        }
    }

    private LocalDateTime parseDateTime(String by) {
        for (DateTimeFormatter formatter : DTFORMATTERS) {
            try {
                return LocalDateTime.parse(by, formatter);
            } catch (DateTimeParseException ignored) {
            }
        }
        for (DateTimeFormatter formatter : DFORMATTERS) {
            try {
                LocalDate date = LocalDate.parse(by, formatter);
                return date.atStartOfDay();
            } catch (DateTimeParseException ignored) {
            }
        }
        try {
            LocalTime time = LocalTime.parse(by, DateTimeFormatter.ofPattern("HHmm"));
            return LocalDateTime.of(LocalDate.now(), time);
        } catch (DateTimeParseException e) {
            return null;
        }
    }


    public String datetoString(LocalDateTime s) {
        return s.format(DateTimeFormatter.ofPattern("dd MMM yyyy HHmm"));
    }

    public String datetoSaveString(LocalDateTime s) {
        if (s == null) {
            return "Invalid date time format. Please use the format dd-MM-yyyy HHmm";
        }
        return s.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"));
    }

    public LocalDateTime getBy() {
        return this.by;
    }

    @Override
    public String toFileFormat() {
        return "D | " + super.toFileFormat() + " | " + this.datetoSaveString(this.getBy());
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.datetoString(this.getBy()) + ")";
    }
}
