import java.time.LocalDate;

public class Deadline extends Task {
    private LocalDate by;

    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
        this.type = TaskType.DEADLINE;
    }

    public LocalDate getBy() {
        return by;
    }

    @Override
    public String toFileFormat() {
        return super.toFileFormat() + " | " + by;
    }

    @Override
    public String toString() {
        return type.getSymbol() + super.toString() + " (by: " + by.format(formatter) + ")";
    }
}