import java.io.Serializable;
import java.time.LocalDateTime;

public class Deadline extends Task implements Serializable {

    // protected String by;
    private final LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    @Override
    public String getType() {
        return "D ";
    }

    @Override
    public String getDescription() {
        return this.description + " | " + this.by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(Task.outputFormat) + "hrs)";
    }
}