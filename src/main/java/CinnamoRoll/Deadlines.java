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
