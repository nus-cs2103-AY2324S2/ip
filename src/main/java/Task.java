import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Task {
    protected String description;
    protected boolean isDone;
    protected TaskType type;
    protected DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy").withLocale(Locale.US);

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    public String toFileFormat() {
        return (this.type == TaskType.TODO ? "T" : this.type == TaskType.DEADLINE ? "D" : "E")
                + " | " + (isDone ? "1" : "0")
                + " | " + description;
    }

    @Override
    public String toString() {
        String status = isDone ? "[X]" : "[ ]";
        return status + " " + description;
    }
}