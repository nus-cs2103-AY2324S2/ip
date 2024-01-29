import java.time.LocalDateTime;

public class Deadline extends Task {

    protected LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    @Override
    public String getDescription() {
        String strBy = convertDate(by);
        return "[D]" + super.getDescription() + "(by: " + strBy + ")";
    }

    public LocalDateTime getBy() {
        return by;
    }
}
