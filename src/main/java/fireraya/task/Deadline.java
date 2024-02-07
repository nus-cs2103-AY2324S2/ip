package fireraya.task;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Deadline extends Task {
    private String by;
    private Date deadline;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public Deadline(String description, Date deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String saveFormat() {
        if (by == null) {
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            return String.format("D|%d|%s|%s", isDone ? 1 : 0, description, df.format(deadline));
        }
        return String.format("D|%d|%s|%s", isDone ? 1 : 0, description, by);
    }

    @Override
    public String toString() {

        if (deadline != null) {
            DateFormat d = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            //System.out.println("Date Invoked!");
            return "[D]" + super.toString() + " (by: " + d.format(deadline) + ")";
        } else {
            return "[D]" + super.toString() + " (by: " + by + ")";
        }
    }
}