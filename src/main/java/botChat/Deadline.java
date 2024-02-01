package botChat;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
            String formattedDateTime = date.format(formatter);
            return formattedDateTime.equals(by);
        } catch (Exception e) {
            return false;
        }
    }

    private String convertDate(String by) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try {
            LocalDate date = LocalDate.parse(by, inputFormatter);
            return date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        } catch (Exception e) {
            try {
                LocalDateTime dateTime = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
                return dateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
            } catch (Exception ex) {
                return by;
            }
        }
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + by + ")";
    }
}
