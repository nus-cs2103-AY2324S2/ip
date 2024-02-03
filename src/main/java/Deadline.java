import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDateTime dueTime;
    private LocalDate dueDate;

    public Deadline(String taskName, boolean isDone, LocalDateTime dueTime) {
        super(taskName, isDone);
        this.dueTime = dueTime;
    }

    public Deadline(String taskName, boolean isDone, LocalDate dueDate) {
        super(taskName, isDone);
        this.dueDate = dueDate;
    }

    @Override
    public String storeData() {
        if (this.dueDate == null) {
            return "deadline|" + super.storeData() + "|" + this.dueTime;
        } else {
            return "deadline|" + super.storeData() + "|" + this.dueDate;
        }
    }

    @Override
    public String toString() {
        if (this.dueDate == null) {
            String timeFormat = this.dueTime.format(DateTimeFormatter.ofPattern("MMM d yyyy KK.mm a"));
            return "[D]" + super.toString()
                    + String.format(" (by: %s)", timeFormat);
        } else {
            String timeFormat = this.dueDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            return "[D]" + super.toString()
                    + String.format(" (by: %s)", timeFormat);
        }
    }
}
