import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate by;
    private DateTimeFormatter targetFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + this.by.format(targetFormatter) + ")";
    }

    @Override
    public boolean getStatus() {
        return super.getStatus();
    }

    @Override
    public String userInputToString() {
        return "[D]" + super.toString() + "(by: " + this.by + ")";
    }
}
