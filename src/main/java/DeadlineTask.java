import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DeadlineTask extends Task {
    
    private LocalDateTime timing;

    DeadlineTask(String description, LocalDateTime timing) {
        super(description);
        this.timing = timing;
    }

    DeadlineTask(String description, LocalDateTime timing, boolean isCompleted) {
        super(description, isCompleted);
        this.timing = timing;
    }

    @Override
    public String toString() {
        String status = super.isCompleted() ? "[X]" : "[ ]";
        String timingString = this.timing.format(DateTimeFormatter.ofPattern(Task.DATETIME_FORMAT_OUTPUT));
        return "[D]" + status + " " + super.getDescription() + " (by: " + timingString + ")";
    }

    @Override
    public String exportToSave() {
        String status = super.isCompleted() ? "1" : "0";
        return "D," + status + "," + super.getDescription() + "," + this.timing.toString();
    }

}
