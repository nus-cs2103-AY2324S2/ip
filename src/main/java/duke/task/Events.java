package duke.task;
import java.time.LocalDate;
public class Events extends Task{
    protected LocalDate start;
    protected LocalDate end;
    
    public Events(String description, LocalDate start, LocalDate end) {
        super(description);
        this.start = start;
        this.end = end;
    }
    
    public LocalDate getStart() {
        return this.start;
    }
    
    public LocalDate getEnd() {
        return this.end;
    }
    
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from " + formatDateForPrinting(this.getStart())
                + " to " + formatDateForPrinting(this.getEnd()) + ")";
    }
}
