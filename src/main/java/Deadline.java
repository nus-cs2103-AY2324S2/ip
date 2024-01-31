import java.time.LocalDate;
import java.time.LocalTime;

public class Deadline extends Task {

    private LocalDate date;
    private LocalTime time;

    public Deadline(String description, LocalDate date, LocalTime time) {
        super(description);
        this.date = date;
        this.time = time;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + date + " " + time + ")";
    }
}
