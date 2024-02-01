import java.time.LocalDate;
import java.time.LocalDateTime;

public class Deadline extends Task{

    private String by;
    private LocalDateTime ldt = null;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        try {
            ldt = DateTimeParser.parseDateTime(by);
        } catch (DukeExceptions e) {
            System.out.println(e.getMessage());
        }
    }


    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + DateTimeParser.toString(this.ldt) + ")";
    }

    @Override
    public String fileToString() {
        return "D | " + super.fileToString() + " | " + this.by;
    }
}
