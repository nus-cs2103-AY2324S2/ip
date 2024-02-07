import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Task {
    private String done;
    private final String msg;

    public Task (String msg) {
        this.msg = msg;
        this.done = "[ ]";
    }

    public void markTask() {
        this.done = "[X]";
    }

    public void unmarkTask() {
        this.done = "[ ]";
    }

    public String formatDate(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    @Override
    public String toString() {
        return done + "  " + msg;
    }
}
