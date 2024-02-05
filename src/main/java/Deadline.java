import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    LocalDate date;
    public Deadline(String description, String dateString, boolean isDone) {
        super(description, isDone);
        isDeadline = true;
        this.date = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public LocalDate getDate() {
        return date;
    }
}
