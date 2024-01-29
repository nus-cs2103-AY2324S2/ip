import java.time.LocalDate;
public class Deadline extends Task {

    protected LocalDate by;

    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDate.parse(by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.getMonth() + " " + by.getDayOfMonth() + " " + by.getYear() + ")";
    }

    public String toFileFormat() {
        String completed = this.isDone ? "1" : "0";
        return "D | " + completed + " | " + this.description + " | " + this.by;
    }
}