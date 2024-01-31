//// Solution below adapted by week2 iP Level-3 Partial solution
import java.time.LocalDateTime;

public class Event extends Task{
    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event(String destription, LocalDateTime from, LocalDateTime to) {
        super(destription);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + this.from + "to: " + this.to + ")";
    }
}
