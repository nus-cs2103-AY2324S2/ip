/**
 * Deadline class
 * This class contains informations about the deadline.
 */
public class Deadline extends Task {
    private String time;

    /**
     * Deadline Constructor
     * @param description
     * @param time
     */
    public Deadline(String description, String time) {
        super(description);
        this.time = time;
    }

    /**
     * toString Method
     * @return a string that describe the deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by:" + this.time + ")";
    }
}

