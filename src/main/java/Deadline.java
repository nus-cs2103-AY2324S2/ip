import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class Deadline extends Task {

    protected LocalDateTime dueDate;

    public Deadline(String description, LocalDateTime newDueDate) {
        super(description);
        this.dueDate = newDueDate;
    }

    public Deadline(String description, String newDueDate) {
        super(description);
        this.dueDate = LocalDateTime.parse(newDueDate);
    }

    public String formattedDueDate() {
        return this.dueDate.format(DateTimeFormatter.ofPattern(this.dateTimeFormat));
    }
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.formattedDueDate());
    }

    @Override
    public String toFileString() {
        return String.format("D,%s,%s", super.toFileString(), this.dueDate);
    }

}
