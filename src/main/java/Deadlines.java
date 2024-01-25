import java.util.*;

class Deadlines extends Task {

    private final String deadline;

    Deadlines(String str, String by) {
        super(str);
        this.deadline = by;
    }

    String getStatusIcon() {
        return (this.is_marked ? "[D][X]" : "[D][ ]");
    }

    public String toString() {
        return super.toString() + "(by:" + this.deadline + ")";
    }

    String added(int length) {
        return "   Got it. I've added this task:\n" + "     " +
                this.getStatusIcon() + " " + this.taskname + "\n" +
                "   Now you have " + String.valueOf(length) + " tasks in the list";
    }
}