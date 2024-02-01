package tasks;
import java.time.LocalDate;

public class ToDo extends Task {
    
    public ToDo(String description) {
        super(description);
        
    }

    @Override
    public String toString() {
        if (this.isMarked()) {
            return "[T][X] " + super.toString();
        } else {
            return "[T][ ] " + super.toString();
        }
    }

    @Override
    public String identifier() {
        return "[T]";
    }

    @Override
    public LocalDate getDeadline() {
        return LocalDate.now();
    }
    
}
