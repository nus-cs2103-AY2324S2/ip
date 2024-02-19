package zhen.task;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Deadline extends Task {
    private LocalDate dueDate = null;
    private String dueDateString;
    public Deadline(String message, LocalDate dueDate) {
        super(message);
        this.dueDate = dueDate;
    }
    public Deadline(String message, String dueDate) {
        super(message);
        this.dueDateString = dueDate;
    }
    @Override
    public String toString() {
        String taskInfo;
        if (isCompleted()) {
            taskInfo = "[D][X] " + getMessage();
        } else {
            taskInfo = "[D][ ] " + getMessage();
        }
        if (dueDate != null) {
            return taskInfo + " (by: "
                    + dueDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy").withLocale(Locale.ENGLISH))
                    + ")" + tag;
        } else {
            return taskInfo + " (by: " + dueDateString + ")" + tag;
        }
    }
}
