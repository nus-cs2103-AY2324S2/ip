package demon;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that has a deadline.
 */
public class Deadline extends Task {

    private LocalDateTime dateTime;

    public Deadline(String description, LocalDateTime dateTime){
        super(description);
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        String description = super.toString();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("h:mm a");
        String formattedDate = dateTime.format(formatter);
        String formattedTime = dateTime.format(timeFormat);
        return "[D]" + description + " (by: " + formattedDate + " " + formattedTime + ")";
    }

    @Override
    public LocalDateTime getDateTime() {
        return this.dateTime;
    }
}
