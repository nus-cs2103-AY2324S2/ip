import java.util.*;
import java.io.*;

/**
 * Deadline is a subclass of Task that stores a task with a completion date.
 * @author Koo Zhuo Hui
 */
public class Deadline extends Task {

    private String by;

    /**
     *
     * @param s The name of the deadline.
     * @param by The time at which the deadline should be completed.
     */
    public Deadline(String s, String by) {
        super(s);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D][" + (super.getStatus() ? "X" : " ") + "] " +
                super.getTask() + "(by: " + by + ")";

    }
}
