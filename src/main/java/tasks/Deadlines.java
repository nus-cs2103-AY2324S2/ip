package tasks;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Deadlines extends Task {
    private LocalDateTime actual_deadline;
    
    public Deadlines(String description, LocalDateTime actual_deadline) {
        super(description);
        this.actual_deadline = actual_deadline;
    }

    @Override
    public LocalDate getDeadline() {
        return this.getAbsoluteDeadline().toLocalDate();
    }
    public LocalDateTime getAbsoluteDeadline() {
        return this.actual_deadline;
    }

    public String toString() {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm'hrs'");
        if (this.isMarked()) {
            return "[D][X] " + super.toString() + "(by: " + this.getAbsoluteDeadline().format(outputFormatter) + ")";
        } else {
            return "[D][ ] " + super.toString() + "(by: " + this.getAbsoluteDeadline().format(outputFormatter) + ")";
        }
    }

    @Override
    public String identifier() {
        return "[D]";
    }


    
}
