import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;


public class Deadlines extends Task {
    public LocalDateTime deadline;

    public Deadlines(String description, LocalDateTime deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");

        String formattedDate = deadline.format(outputFormatter);


        return String.format("[D]%s (by: %s)", super.toString(), formattedDate);
    }
}
