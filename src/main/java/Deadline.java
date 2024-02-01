import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        if (isValidDateFormat(by)) {
            this.by = by;
        } else {
            this.by = convertDate(by);
        }
    }

    private boolean isValidDateFormat(String by) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        try {
            LocalDate date = LocalDate.parse(by, formatter);
            String formattedDate = date.format(formatter);
            return formattedDate.equals(by);
        } catch (Exception e) {
            return false;
        }
    }

    private String convertDate(String by) {
        LocalDate date = LocalDate.parse(by);
        return date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + by + ")";
    }
}
