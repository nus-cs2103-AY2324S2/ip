package fireraya.task;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This class holds a DoAfter task.
 *
 * It is extended from the parent class Task.
 */
public class DoAfter extends Task {
    private String after;
    private Date afterDate;

    /**
     * Constructor for a DoAfter task.
     *
     * @param description description of the task.
     * @param after String of date after which the task should be started.
     */
    public DoAfter(String description, String after) {
        super(description);
        this.after = after;
    }

    /**
     * Overloaded constructor for a DoAfter task.
     *
     * @param description description of the task.
     * @param afterDate Date after which the task should be started as a java util Date.
     */
    public DoAfter(String description, Date afterDate) {
        super(description);
        this.afterDate = afterDate;
    }

    /**
     * Overrides the format to save the file on local device.
     *
     * @return string of the saved format of the task.
     */
    @Override
    public String saveFormat() {
        if (after == null) {
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            return String.format("A|%d|%s|%s", isDone ? 1 : 0, description, df.format(afterDate));
        }
        return String.format("A|%d|%s|%s", isDone ? 1 : 0, description, after);
    }

    /**
     * Overrides a string with information about this task.
     *
     * @return string format of this task.
     */
    @Override
    public String toString() {

        if (afterDate != null) {
            DateFormat d = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            return "[A]" + super.toString() + " (do after: " + d.format(afterDate) + ")";
        } else {
            return "[A]" + super.toString() + " (do after: " + after + ")";
        }
    }
}