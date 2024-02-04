import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Deadline extends Task {
    protected LocalDateTime by;

    public Deadline (String description, String by) {
        super(description);
        this.by = LocalDateTime.parse(by, Constants.INPUT_FORMATTER);
    }

    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(Constants.OUTPUT_FORMATTER) + ")";
    }

    @Override
    public String serializeToCommand(int taskIndex) {
        return "deadline " + description + " /by " + by.format(Constants.INPUT_FORMATTER) + "\n"
            + serializeDoneMark(taskIndex);
    }
}
