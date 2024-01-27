import java.time.LocalDate;

public class Deadline extends Task {

    protected LocalDate by;

    public Deadline(String description, String by) throws DukeException {
        super(description);
        this.by = DateChecker.parseDate(by);
    }

    @Override
    public String saveData() {
        return "D | " + (isDone() ? "1" : "0") + " | " + getDescription() + " | " + this.by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + DateChecker.formatDate(this.by) + ")";
    }
}
