/**
 * A class that encapsulates the ToDo tasks, a type of Task.
 * 
 * @author: CHEN WENLONG
 * @version: CS2103T AY23/24 Semester 2
 */
public class Event extends Task {
    /** A String value that represent the type of Task, Efor Event. */
    private static final String TYPE = "E";

    /** A String value that represents the time of the start of event. */
    private String startTime;

    /** A String value that represents the time of the end of event. */
    private String endTime;

    /**
     * Constructor for the ToDo.
     * 
     * @param name A String value that states the name of the Task.
     * @param startTime A String representation of the start of the event.
     * @param endTime A String representation of the end of the event.
     */
    public Event(String name, String startTime, String endTime) {
        super(name, TYPE);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Converts the data (Event) here into a format to be stored in the file.
     * 
     * @return String representation of the Event to be store in local disk.
     */
    @Override
    public String convertToFormat() {
        return super.convertToFormat() + " | " + this.startTime + " | " + this.endTime;
    }

    /**
     * String representation of an Event.
     * 
     * @return Returns the String representation of an Event.
     */
    @Override
    public String toString() {
        return super.toString() + " (from: " + this.startTime + " to: " + this.endTime + ")";
        // [T][X] name (from: startTime to: endTime)
    }
}
