package CinnamoRoll;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Creates a deadline task with tasklist and deadline as an input
 */
class Deadlines extends Task {

    private final LocalDateTime deadline;
    private final DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");

    /**
     * Constructs a new Deadline task with the given description and deadline.
     *
     * @param str     The description of the Deadline task.
     * @param by      The deadline of the task represented as a LocalDateTime object.
     */
    Deadlines(String str, LocalDateTime by) {
        super(str);
        this.deadline = by;
    }

    /**
     * Constructs a new Deadline task with the given description, deadline, and marked status.
     *
     * @param str     The description of the Deadline task.
     * @param by      The deadline of the task represented as a LocalDateTime object.
     * @param marked  A boolean indicating whether the task is marked as completed.
     */
    Deadlines(String str, LocalDateTime by, boolean marked) {
        super(str, marked);
        this.deadline = by;
    }

    String getStatusIcon() {
        return (this.getMarked() ? "[D][X]" : "[D][ ]");
    }

    /**
     * Returns a string that describes the number of tasks in the list,
     * description of the (deadline) task, deadline, task type and the marked status
     *
     * @param length denotes the length of the list
     */
    String addTask(int length) {
        return String.format("Got it. I've added this task:%n   %s%n"
                + "Now you have %d tasks in the list", this.toString(), length);
    }

    /**
     * Outputs a string output of an event task that contains taskname, task type,
     * and deadline.
     */
    public String toString() {
        return String.format("%s (by: %s)", super.toString(), this.deadline.format(format));
    }

    /**
     * Returns a string of the task so that it can be stored into the
     * database with a correct format to load data in future
     */
    public String storeInFile() {
        if (this.isMarked) {
            return "D | X | " + this.taskname + " /by "
                    + this.deadline.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        } else {
            return "D |   | " + this.taskname + " /by "
                    + this.deadline.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        }
    }
}
