import java.time.LocalDate;
import java.time.LocalDateTime;

public class Event extends Task {

    LocalDate start;
    LocalDate end;

    public Event(String name, LocalDate start, LocalDate end) {
        super(name);
        this.start = start;
        this.end = end;
    }

    @Override
    public String fullStatus() { //TODO: add type of task later
        String checkbox;
        if (isDone) {
            checkbox = "[E][X] ";
        } else {
            checkbox = "[E][ ] ";
        }
        return checkbox + name + " (from: " + start +  " to: " + end + ")";
    }
}
