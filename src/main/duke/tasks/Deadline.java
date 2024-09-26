package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * An encapsulation of a deadline type task.
 *
 * @author Lim Zi Jia
 */
public class Deadline extends Task {
    /** The time of the deadline. */
    private LocalDate time;

    /**
     * Constructor for Deadline.
     * @param done True if the task is done.
     * @param name Name of the Task.
     * @param time Time of the deadline.
     */
    public Deadline(boolean done, String name, String time) {
        super(done, name);
        this.time = LocalDate.parse(time);
    }

    /**
     * Constructor for Deadline.
     * @param name Name of the Task.
     * @param time Time of the deadline.
     */
    public Deadline(String name, String time) {
        super(name);
        this.time = LocalDate.parse(time);
    }

    @Override
    public String taskToSavedString() {
        return String.format("D,%s,%s,%s",
                this.done ? '1' : '0',
                this.name,
                this.time.format(DateTimeFormatter.ISO_LOCAL_DATE));
    }

    @Override
    public String toString() {
        return String.format("[D][%s] %s (by: %s)\n",
                this.done ? "X" : " ",
                this.name,
                this.time.format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }

        Deadline deadline = (Deadline) obj;
        return this.name.equals(deadline.name)
                && this.done == deadline.done
                && this.time.equals(deadline.time);
    }
}
