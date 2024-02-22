package podz.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task in the task manager.
 */
public class Event extends Task {

    protected String from, to, formattedFrom, formattedTo;

    /**
     * Constructs an Event object with the specified description, start date and end date.
     * 
     * @param description the description of the event.
     * @param from the start date and time of the event.
     * @param to the end date and time of the event.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
        
        // splits the from/to information to an array containing its date and time
        String[] fromDateTime = from.split(" ");
        String[] toDateTime = to.split(" ");

        this.formattedFrom = formatDT(fromDateTime);
        this.formattedTo = formatDT(toDateTime);
    }

    /**
     * Formats the date and time into a specific format.
     * 
     * @param dateAndTime the array containing date and time information.
     * @return the formatted date and time string.
     */
    private String formatDT(String[] dateAndTime) {
        String formattedString;
        boolean isEventWithDateAndTime = dateAndTime.length >= 2;
        
        if (isEventWithDateAndTime) {
            LocalDate d1 = LocalDate.parse(dateAndTime[0]);
            int hour = Integer.parseInt(dateAndTime[1].substring(0, 2));
            int minute = Integer.parseInt(dateAndTime[1].substring(2));
            LocalDateTime dateTime = d1.atTime(hour, minute);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd yyyy HH:mm");
            formattedString = dateTime.format(formatter);
        } else {
            LocalDate d1 = LocalDate.parse(dateAndTime[0]);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd yyyy");
            formattedString = d1.format(formatter);
        }

        return formattedString;
    }

    /**
     * Returns the saved format of the event task.
     * 
     * @return the saved format of the event task.
     */
    @Override
    public String savedFormat() {
        return "E " + "|" + super.savedFormat() + " | " + this.from + " | " + this.to;
    }

    /**
     * Returns a string representation of the event task.
     * 
     * @return a string representing the event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.formattedFrom 
                + " to: " + this.formattedTo + ")";
    }
}
