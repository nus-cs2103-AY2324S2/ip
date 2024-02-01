/**
 * Represents a Events Task.
 * <p>
 * This class is the representation of a Events task.
 * It extends from its parent class the Task class.
 */
package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Events extends Task {
    private String from;
    private String to;
    private LocalDateTime fromDateTime;
    private LocalDateTime toDateTime;
    final static DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");


    /**
     * Creates a Events object.
     * Will call the super constructor with the task name variable.
     *
     * @param c The name of the task.
     * @param from The start of the event.
     * @param to The end of the event.
     */
    public Events(String c, String from, String to) {
        super(c);
        this.from = from.split("from ")[1];
        this.to = to.split("to ")[1];
        this.fromDateTime = LocalDateTime.parse(from.split("from ")[1].trim(), DATE_FORMAT);
        this.toDateTime = LocalDateTime.parse(to.split("to ")[1].trim(), DATE_FORMAT);
    }

    /**
     * Returns a string representation of this Events object for storage in DataWriter.
     * This includes the formating required for the reader to split and read it.
     * 
     * @return a formatted string representation of this object. 
     */
    @Override
    public String formatDataLine() {
        return "Events|" + super.command + "|from " + this.from + "|to " + this.to;
    }

    /**
     * Returns a string representation of this Events.
     * This includes an indicator that this is a Events object.
     * 
     * @return a string representation of this Events object.
     */
    @Override
    public String toString() {
        String s = "[E]" + super.toString() + "(from: " + this.fromDateTime.format(DATE_FORMAT) + " to: " + this.toDateTime.format(DATE_FORMAT) + ")";
        return s;
    }
}
