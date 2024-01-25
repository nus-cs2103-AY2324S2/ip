import java.io.EOFException;

/**
 * Represents a Deadlines Task.
 * <p>
 * This class is the representation of a Deadlines task.
 * It extends from its parent class the Task class.
 */
public class Deadlines extends Task {
    private String end;

    /**
     * Creates a Deadlines object.
     * Will call the super constructor with the task name variable.
     *
     * @param c The name of the task.
     * @param end The end of the event.
     */
    public Deadlines(String c, String end) {
        super(c);
        this.end = end.split("by ")[1];
    }

    /**
     * Returns a string representation of this Deadlines.
     * This includes an indicator that this is a Deadlines object.
     * 
     * @return a string representation of this Deadlines object.
     */
    @Override
    public String toString() {
        String s = "[D]" + super.toString() +"(by: "+this.end+")";
        return s;
    }
}
