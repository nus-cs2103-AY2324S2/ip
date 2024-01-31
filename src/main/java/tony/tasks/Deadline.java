package tony.tasks;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDateTime dueDate;


    public Deadline(String description, LocalDateTime dueDate) {
        super(description);
        this.type = TaskType.DEADLINE;
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (" + dueDate.format(DateTimeFormatter.ofPattern("MMM d yyyy hh mm")) + ")";
    }

    @Override
    public String formattedString() {

        return "D" + super.formattedString() + "|" + dueDate.toString();
    }

    @Override
    public String getType() {
        return type.toString();
    }
}