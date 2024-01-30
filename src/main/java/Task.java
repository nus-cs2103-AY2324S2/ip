import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;

public abstract class Task {
    protected String description;
    protected boolean isDone = false;

    @Override
    public String toString() {
        return this.description;
    }

    public String getStatusIcon() {
        return this.isDone ? "X": " "; // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone(){
        this.isDone = false;
    }

    public String toWrite(){
        return (isDone ? 1 : 0) + " | " + this.description;
    }

    public String dateTimeFormat(LocalDateTime dateTime){
        return dateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
    }

    public abstract String getTaskIcon();
}
