/**
 * Represents a Events Task.
 * <p>
 * This class is the representation of a Events task.
 * It extends from its parent class the Task class.
 */
public class Events extends Task {
    private String from;
    private String to;

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
    }

    /**
     * Returns a string representation of this Events.
     * This includes an indicator that this is a Events object.
     * 
     * @return a string representation of this Events object.
     */
    @Override
    public String toString() {
        String s = "[E]" + super.toString() + "(from: " + this.from + "to: " + this.to + ")";
        return s;
    }
}
