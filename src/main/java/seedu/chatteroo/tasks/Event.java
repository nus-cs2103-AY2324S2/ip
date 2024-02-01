package seedu.chatteroo.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.DateTimeException;

public class Event extends Task {
    protected LocalDateTime from, to;
    protected String taskType = "E";

    public Event(String description, String from, String to) {
        super(description);
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        try {
            this.from = LocalDateTime.parse(from.trim(), dateFormat);
            this.to = LocalDateTime.parse(to.trim(), dateFormat);
        } catch (DateTimeException e) {
            System.out.println("Please enter a valid date and time in the format yyyy-mm-dd HHmm!\n" +
                    "For example, 2024-01-31 1800");
        }
    }
    //Overridden toString method to print type of task, description and time
    @Override
    public String toString() {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm");
        String formattedFrom = this.from.format(dateFormat);
        String formattedTo = this.to.format(dateFormat);
        return "[E]" + super.toString() + " (from: " + formattedFrom + " to: " + formattedTo + ")";
    }

    public LocalDateTime getFrom() {
        return this.from;
    }
    public LocalDateTime getTo() {
        return this.to;
    }

}
