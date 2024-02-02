package duke.task;
import java.time.LocalDate;
public class Deadlines extends Task{
    protected LocalDate by;
    
    public Deadlines(String description, LocalDate by) {
        super(description);
        this.by = by;
    }
    
    public LocalDate getBy() {
        return this.by;
    }
    
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + formatDateForPrinting(this.getBy()) + ")";
    }
}
