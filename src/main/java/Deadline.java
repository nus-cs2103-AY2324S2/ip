import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDateTime by;

    public Deadline(String name, LocalDateTime by) {
        super(name);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a")) +
                ")";
    }

    @Override
    public String addToFile() {
        return "D | " + super.addToFile() + " | " + by.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm")) + "\n";
    }

}
