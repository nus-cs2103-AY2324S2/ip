import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.format.DateTimeFormatter;
public class Event extends Task{

    protected LocalDate start;
    protected LocalDate end;

    Event(String name, LocalDate start, LocalDate end) {
        super(name);
        this.start = start;
        this.end = end;
    }
    @Override
    protected String taskTypeDisplay() {
        return "[E]";
    }
    @Override
    public String toString() {
        return String.format("%s%s %s (from: %s to: %s)", this.taskTypeDisplay(), this.completionDisplay(), this.name, this.start.format(DateTimeFormatter.ofPattern("dd MMM yyyy")), this.end.format(DateTimeFormatter.ofPattern("dd MMM yyyy")));
    }
    @Override
    public String storeFormat() {
        String completeFormat = complete ? "1" : "0";
        return String.format("%s | %s | %s | %s | %s", "D", completeFormat, name, start.format(DateTimeFormatter.ISO_DATE), end.format(DateTimeFormatter.ISO_DATE));
    }
}
