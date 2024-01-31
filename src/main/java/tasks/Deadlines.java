package tasks;
import java.time.LocalDate;
import java.time.LocalDateTime;


public class Deadlines extends Task {
    private String deadline;
    private LocalDateTime actual_deadline;
    
    public Deadlines(String description, String deadline, LocalDateTime actual_deadline, int num) {
        super(description, num);
        this.deadline = deadline;
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
        if (this.isMarked()) {
            return "[D][X] " + super.toString() + "(by: " + this.deadline.substring(3) + ")";
        } else {
            return "[D][ ] " + super.toString() + "(by: " + this.deadline.substring(3) + ")";
        }
    }

    @Override
    public String identifier() {
        return "[D]";
    }


    
}
