import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
public class Event extends Task{
    LocalDateTime startDate;
    LocalDateTime endDate;
    public Event(String name, LocalDateTime startDate, LocalDateTime endDate, boolean isDone) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        String str = "";
        str = String.format("[E]%s (from: %s to: %s)", super.toString(),
                startDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")),
                endDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
        return str;
    }

    @Override
    public String convertToText() {
        String str = "";
        str = String.format("%s event %s /from %s /to %s", isDone, name, startDate, endDate);
        return str;
    }
}
