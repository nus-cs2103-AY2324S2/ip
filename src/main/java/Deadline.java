import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;

public class Deadline extends Task {
    private String deadline;
    private Date date;
    private LocalTime time;

    Deadline(String description, boolean status, String deadline) {
        super(TaskType.DEADLINE, description, status);
        this.deadline = deadline;
    }

    Deadline(String description, boolean status, Date date) {
        super(TaskType.DEADLINE, description, status);
        this.date = date;
    }
    Deadline(String description, boolean status, LocalTime time) {
        super(TaskType.DEADLINE, description, status);
        this.time = time;
    }
    Deadline(String description, boolean status, Date date, LocalTime time) {
        super(TaskType.DEADLINE, description, status);
        this.date = date;
        this.time = time;
    }

    public String getDeadline() {
        if (this.deadline == null) {
            if (this.date == null) {
                return this.time.toString();
            } else if (this.time == null) {
                return this.date.toString();
            } else {
                return this.date.toString() + " " + this.time.toString();
            }
        }
        return this.deadline;
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.getDeadline() + ")";
    }
}
