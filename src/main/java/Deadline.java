/**
 * A class that encapsulates the Deadlines tasks, a type of Task.
 * 
 * @author: CHEN WENLONG
 * @version: CS2103T AY23/24 Semester 2
 */
public class Deadline extends Task {
    /** A String value that represent the type of Task, D for Deadline. */
    private static final String TYPE = "D";

    /** A String value that represents the time of deadline. */
    private String endTime;

    /**
     * Constructor for the ToDo.
     * 
     * @param name A String value that states the name of the Task.
     * @param time A String to state the time of deadline.
     */
    public Deadline(String name, String endTime) {
        super(name, TYPE);
        this.endTime = endTime;
    }

    /**
     * String representation of a Deadline.
     * 
     * @return Returns the String representation of a Deadline.
     */
    @Override
    public String toString() {
        return super.toString() + " (by: " + this.endTime + ")"; // [T][X] name (by: time)
    }
}
