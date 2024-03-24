package fireraya.task;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This class holds a Deadline task.
 *
 * It is extended from the parent class Task.
 */
public class Deadline extends Task {
    private String by;
    private Date deadline;

    /**
     * Constructor for a Deadline task.
     *
     * @param description description of the task.
     * @param by deadline of the task as a string
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Overloaded constructor for a Deadline task.
     *
     * @param description description of the task.
     * @param deadline deadline of the task as a java util Date.
     */
    public Deadline(String description, Date deadline) {
        super(description);
        this.deadline = deadline;
    }

    /**
     * Overrides the format to save the file on local device.
     *
     * @return string of the saved format of the task.
     */
    @Override
    public String saveFormat() {
        if (by == null) {
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            return String.format("D|%d|%s|%s", isDone ? 1 : 0, description, df.format(deadline));
        }
        return String.format("D|%d|%s|%s", isDone ? 1 : 0, description, by);
    }

    /**
     * Overrides a string with information about this task.
     *
     * @return string format of this task.
     */
    @Override
    public String toString() {

        if (deadline != null) {
            DateFormat d = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            return "[D]" + super.toString() + " (by: " + d.format(deadline) + ")";
        } else {
            return "[D]" + super.toString() + " (by: " + by + ")";
        }
    }
}