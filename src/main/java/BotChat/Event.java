package BotChat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        if (isValidDateFormat(from) && isValidDateFormat(to)) {
            this.from = from;
            this.to = to;
        } else {
            this.from = convertDate(from);
            this.to = convertDate(to);
        }
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
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
        return "[E] " + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
