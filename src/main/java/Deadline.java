import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
public class Deadline extends Task{
    protected LocalDate by;

    public Deadline(String description, String by) {
        super(description);
        this.by = parseDate(by);
    }

    private LocalDate parseDate(String dateString) {
        try {
            return LocalDate.parse(dateString);
        } catch (DateTimeParseException e) {
            System.out.println("Unable to parse the date: " + dateString);
            return null; // Or handle it another way
        }
    }

    private LocalDate parseDateTime(String dateTimeString) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
            return LocalDate.parse(dateTimeString, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Unable to parse the date and time: " + dateTimeString);
            return null; // Or handle it another way
        }
    }

    private String formatDate(LocalDate date) {
        if (date == null) {
            return "no date";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return date.format(formatter);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + formatDate(by) + ")";
    }
    @Override
    public String toFileFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
        return "D | " + super.toFileFormat() + " | " + (by != null ? by.format(formatter) : "");
    }

    public static Task fromFileFormat(String line) throws JayneException {
        String[] parts = line.split(" \\| ");
        if (parts.length < 4 || !"D".equals(parts[0])) {
            throw new JayneException("Invalid line format for Deadline");
        }
        Deadline task = new Deadline(parts[2], parts[3]);
        if ("1".equals(parts[1])) {
            task.markAsDone();
        }
        return task;
    }

}
