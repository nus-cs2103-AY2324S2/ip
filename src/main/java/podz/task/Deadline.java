package podz.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import podz.exceptions.PodzException;

/**
 * Represents a deadline task in the task manager.
 */
public class Deadline extends Task {

    protected String by, formattedBy, time;

    /**
     * Constructs a Deadline object with the specified description and deadline.
     * 
     * @param description the description of the deadline task.
     * @param by the deadline date and time.
     * @throws PodzException 
     */
    public Deadline(String description, String by) throws PodzException {
        super(description);
        this.by = by;

        String[] dateAndTime = by.split(" ");
        boolean isDeadlineWithDateAndTime = dateAndTime.length >= 2;

        try {
            if (isDeadlineWithDateAndTime) {
                LocalDate d1 = LocalDate.parse(dateAndTime[0]);
                int hour = Integer.parseInt(dateAndTime[1].substring(0, 2));
                int minute = Integer.parseInt(dateAndTime[1].substring(2));
                LocalDateTime dateTime = d1.atTime(hour, minute);
    
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd yyyy HH:mm");
                String formattedString = dateTime.format(formatter);
                this.formattedBy = formattedString;
            } else {
                LocalDate d1 = LocalDate.parse(dateAndTime[0]);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd yyyy");
                String formattedString = d1.format(formatter);
                this.formattedBy = formattedString;
            }
        } catch (DateTimeParseException e) {
            throw new PodzException("Oops!! You entered an invalid date/time format.");
        }
        
    }

    /**
     * Returns the saved format of the deadline task.
     * 
     * @return the saved format of the deadline task.
     */
    @Override
    public String savedFormat() {
        return "D " + "|" + super.savedFormat() + " | " + this.by;
    }

    /**
     * Returns a string representation of the deadline task.
     * 
     * @return a string representing the deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.formattedBy + ")";
    }
}
