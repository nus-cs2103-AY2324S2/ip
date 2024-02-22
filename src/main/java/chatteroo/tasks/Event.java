package chatteroo.tasks;

import chatteroo.ChatterooException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.DateTimeException;

/**
 * Represents an event task with a description, a status of whether it is done and a start and end time.
 */
public class Event extends Task {
    protected LocalDateTime from, to;
    protected String taskType = "E";

    /**
     * Constructor for the Event class.
     * @param description The description of the task.
     * @param from The start time of the task.
     * @param to The end time of the task.
     * @throws ChatterooException If the date and time is not in the correct format.
     */
    public Event(String description, String from, String to) throws ChatterooException {
        super(description, "E");
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        try {
            this.from = LocalDateTime.parse(from.trim(), dateFormat);
            this.to = LocalDateTime.parse(to.trim(), dateFormat);
        } catch (DateTimeException e) {
            throw new ChatterooException("Please enter a valid date and time in the format:\n"
                    + "YYYY-MM-DD HHmm!\n"
                    + "For example, 2024-01-31 1800");
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
