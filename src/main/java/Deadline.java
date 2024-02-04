import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Deadline extends Task{

    private final String TYPE = "D";
    protected LocalDate by;

    public Deadline(String description, String by) throws DukeException {
        super(description);
        try {
            this.by = LocalDate.parse(by);
        } catch (DateTimeParseException e) {
            throw new DukeException("Please enter a valid date in the format yyyy-mm-dd");
        }
    }

    /*
     * Constructs Deadline object with description and by as LocalDate objects.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    public Deadline(String description, LocalDate by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    @Override
    public String getType() {
        return TYPE;
    }

    @Override
    public String toString() {
        return "[" + TYPE + "]" + super.toString() + " (by: " + by.format(Task.DATE_TIME_FORMATTER) + ")";
    }

    @Override
    public String toFileString() {
        return TYPE + " | " + (isDone ? "1" : "0") + " | " + description + " | " + by;
    }

    public static Deadline DeadlineFromFileString(String fileString) {
        String[] taskDetails = fileString.split(" \\| ");
        boolean isDone = taskDetails[1].equals("1");
        String description = taskDetails[2];
        LocalDate by = LocalDate.parse(taskDetails[3]);
        return new Deadline(description, by, isDone);
    }
}
