import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Actions.Action {
    protected LocalDateTime by;

    /**
     * Constructor
     * @param description task to be taken
     * @param by time
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     *
     * @return Date of the deadline
     */
    public LocalDateTime getBy() {
        return this.by;
    }

    /**
     *
     * @return task output
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: "
                + this.by.format(DateTimeFormatter.ofPattern("dd MMM yyyy hh:mma")) + ")";
    }
}
