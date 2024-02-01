/**
 * Represents a Deadlines Task.
 * <p>
 * This class is the representation of a Deadlines task.
 * It extends from its parent class the Task class.
 */
package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadlines extends Task {
    private String end;
    private LocalDateTime endDateTime;
    final static DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    /**
     * Creates a Deadlines object.
     * Will call the super constructor with the task name variable.
     *
     * @param c The name of the task.
     * @param end The end of the event.
     */
    public Deadlines(String c, String end) {
        super(c);
        this.end = end;
        this.endDateTime = LocalDateTime.parse(end.split("by ")[1], DATE_FORMAT);
    }

    /**
     * Returns a string representation of this Deadlines object for storage in DataWriter.
     * This includes the formating required for the reader to split and read it.
     * 
     * @return a formatted string representation of this object. 
     */
    @Override
    public String formatDataLine() {
        return "Deadlines|" + super.command + "|" + this.end;
    }

    /**
     * Returns a string representation of this Deadlines.
     * This includes an indicator that this is a Deadlines object.
     * 
     * @return a string representation of this Deadlines object.
     */
    @Override
    public String toString() {

        String s = "[D]" + super.toString() + "(by: " + this.endDateTime.format(DATE_FORMAT) + ")";
        return s;
    }
}
