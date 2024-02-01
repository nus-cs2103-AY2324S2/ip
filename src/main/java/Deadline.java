import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDateTime dueTime;
    private LocalDate dueDate;

    public Deadline(String taskName, boolean done, LocalDateTime dueTime) {
        super(taskName, done);
        this.dueTime = dueTime;
    }

    public  Deadline(String taskName, boolean done, LocalDate dueDate) {
        super(taskName, done);
        this.dueDate = dueDate;
    }

    @Override
    public String storeData() {
        if (this.dueDate == null) {
            return super.storeData() + " " + this.dueTime;
        } else {
            return super.storeData() + " " + this.dueDate;
        }
    }

    @Override
    public String toString() {
        if (this.dueDate == null) {
            String timeFormat = this.dueTime.format(DateTimeFormatter.ofPattern("MMM d yyyy KK.mm a"));
            return "[D]" + super.toString()
                    + String.format(" (by: %s)", timeFormat);
        } else {
            String timeFormat = this.dueTime.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            return "[D]" + super.toString()
                    + String.format(" (by: %s)", timeFormat);
        }
    }
}
