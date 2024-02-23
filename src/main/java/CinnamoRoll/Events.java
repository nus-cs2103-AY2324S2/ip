package CinnamoRoll;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Creates an event task with tasklist, start and end time as an input
 */
class Events extends Task {
    private final LocalDateTime from;
    private final LocalDateTime to;
    private final DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");

    /**
     * Constructs a new Event task with the given description, start time, and end time.
     *
     * @param str   The description of the Event task.
     * @param from  The start time of the event represented as a LocalDateTime object.
     * @param to    The end time of the event represented as a LocalDateTime object.
     */
    Events(String str, LocalDateTime from, LocalDateTime to) {
        super(str);
        this.from = from;
        this.to = to;
    }

    /**
     * Constructs a new Event task with the given description, start time, end time, and marked status.
     *
     * @param str     The description of the Event task.
     * @param from    The start time of the event represented as a LocalDateTime object.
     * @param to      The end time of the event represented as a LocalDateTime object.
     * @param marked  A boolean indicating whether the task is marked as completed.
     */
    Events(String str, LocalDateTime from, LocalDateTime to, boolean marked) {
        super(str, marked);
        this.from = from;
        this.to = to;
    }

    String getStatusIcon() {
        return (this.getMarked() ? "[E][X]" : "[E][ ]");
    }

    /**
     * Returns a string that describes the number of tasks in the list,
     * description of the (event) task, starting the and ending time of
     * the event task with getStatusIcon with task type and the marked status
     *
     * @param length denotes the length of the list
     */
    String addTask(int length) {
        return String.format("Got it. I've added this task:%n   "
                + "%s%nNow you have %d tasks in the list", this.toString(), length);
    }

    /**
     * Outputs a string output of an event task that contains taskname, task type,
     * start and end time.
     */
    public String toString() {
        return String.format("%s (from: %s to: %s)", super.toString(),
                this.from.format(format), this.to.format(format));
    }

    /**
     * Returns a string of the task so that it can be stored into the
     * database with a correct format to load data in future
     */
    public String storeInFile() {
        if (this.isMarked) {
            return "E | X | " + this.taskname + " /from "
                    + this.from.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) + " /to "
                    + this.to.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        } else {
            return "E |   | " + this.taskname + " /from "
                    + this.from.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) + " /to "
                    + this.to.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        }
    }
}
