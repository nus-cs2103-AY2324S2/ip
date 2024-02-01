package CinnamoRoll;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class Deadlines extends Task {

    private final LocalDateTime deadline;
    private final DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");

    Deadlines(String str, LocalDateTime by) {
        super(str);
        this.deadline = by;
    }

    Deadlines(String str, LocalDateTime by, boolean marked) {
        super(str, marked);
        this.deadline = by;
    }

    String getStatusIcon() {
        return (this.is_marked ? "[D][X]" : "[D][ ]");
    }

    /**
     * Returns a string that describes the number of tasks in the list,
     * description of the (deadline) task, deadline, task type and the marked status
     *
     * @param length denotes the length of the list
     */
    String added(int length) {
        return String.format("Got it. I've added this task:%n   %s%n" +
                "Now you have %d tasks in the list", this.toString(), length);
    }

    public String toString() {
        return String.format("%s (by: %s)", super.toString(), this.deadline.format(format));
    }
}
