import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private static final String TASK_TYPE = "D";
    private LocalDateTime by;

    public Deadline(String title, LocalDateTime by) {
        super(title);
        this.by = by;
    }

    private String displayDate(LocalDateTime by) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm");
        return by.format(formatter);
    }

    @Override
    public String toCsv() {
        return String.format("%s,%s,%s,%s", TASK_TYPE, this.getIsMarked() ? "T" : "F", this.getTitle(), this.by);
    }

    @Override
    public String toString() {
        return String.format("[%s]%s (By: %s)", TASK_TYPE, super.toString(), displayDate(this.by));
    }
}
