package gronk;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Deadline class.
 * Subclass of Task, with a deadline involved.
 */
public class Deadline extends Task {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private LocalDate time;

    /**
     * Constructor for Deadline object.
     * @param d String containing description of Task.
     * @param s Integer representing completion status.
     * @param t String representing deadline of Task.
     */
    public Deadline(String d, int s, LocalDate t) {
        super(d, s);
        this.time = t;
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
            LocalDate deadline = LocalDate.parse(time, DATE_FORMAT);
            return new Deadline(description, status, deadline);
        } catch (DateTimeParseException e) {
            System.out.println("\tError, please input the time in dd/MM/yyyy format.");
            return null;
        }
    }

    public String getTime() {
        return this.time.format(DATE_FORMAT);
    }

    @Override
    public String statusMessage() {
        if (this.getStatus() == 0) {
            return "Well done! Deadline: " + this.getDesc() + " finished.";
        } else {
            return "Deadline updated. Deadline: " + this.getDesc() + " marked as incomplete.";
        }
    }

    @Override
    public String saveFormat() {
        return Integer.toString(this.getStatus())
                + "," + this.getDesc()
                + "," + this.time.format(DATE_FORMAT);
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
