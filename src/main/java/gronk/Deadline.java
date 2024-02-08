package gronk;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Deadline class.
 * Subclass of Task, with a deadline involved.
 */
public class Deadline extends Task {
    private static final DateTimeFormatter INPUTFORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private static final DateTimeFormatter OUTPUTFORMAT = DateTimeFormatter.ofPattern("MMM d yyyy");
    private LocalDateTime time;

    /**
     * Constructor for Deadline object.
     * @param d String containing description of Task.
     * @param s Integer representing completion status.
     * @param t String representing deadline of Task.
     */
    public Deadline(String d, int s, String t) {
        super(d, s);
        try {
            this.time = LocalDateTime.parse(t, INPUTFORMAT);
        } catch (DateTimeParseException e) {
            System.out.println("Please input the time in yyyy-MM-dd HH:mm format.");
        }
    }

    /**
     * Factory constructor for Deadline object.
     *
     * @param description String containing description of Task.
     * @param status Integer representing completion status.
     * @param time String representing deadline of Task.
     * @return New Deadline object.
     */
    public static Deadline createDeadline(String description, int status, String time) {
        try {
            LocalDateTime deadline = LocalDateTime.parse(time, INPUTFORMAT);
            return new Deadline(description, status, time);
        } catch (DateTimeParseException e) {
            return null;
            //System.out.println("\tError, please input the time in yyyy-MM-dd HH:mm format.");
        }
    }

    public String getTime() {
        return this.time.format(OUTPUTFORMAT);
    }

    @Override
    public String statusMessage() {
        if (this.getStatus() == 0) {
            return "\tWell done! Deadline: " + this.getDesc() + " finished.";
        } else {
            return "\tDeadline updated. Deadline: " + this.getDesc() + " marked as incomplete.";
        }
    }

    @Override
    public String saveFormat() {
        return Integer.toString(this.getStatus())
                + "," + this.getDesc()
                + "," + this.time.format(INPUTFORMAT);
    }

    @Override
    public String toString() {
        if (this.getStatus() == 0) {
            return "[D] [ ] " + this.getDesc() + " (by: " + this.getTime() + ")";
        } else {
            return "[D] [X] " + this.getDesc() + " (by: " + this.getTime() + ")";
        }
    }
}
