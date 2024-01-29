import java.time.LocalDateTime;

public class Deadline extends Task {

    private LocalDateTime by;

    public Deadline(String name, LocalDateTime by) {
        super(name);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + super.formatOutputDate(by) + ")";
    }

    @Override
    public String addToFile() {
        return "D | " + super.addToFile() + " | " + super.formatInputDate(by) + "\n";
    }

}
