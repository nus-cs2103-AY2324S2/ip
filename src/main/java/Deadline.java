import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class Deadline extends Task{

    protected LocalDate by;
    protected String originalBy;
    private static final DateTimeFormatter OUTPUT_DATE_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy");

    public Deadline(String description, String by) {
        super(description);
        this.originalBy = by;
        this.by = LocalDate.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    @Override
    public String toFileString() {
        // Format: D | 0/1 | description | by date
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + this.originalBy;
    }

    public String getBy() {
        return  " (by: " + this.by.format(OUTPUT_DATE_FORMATTER) + ")";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + this.getBy();
    }
}
