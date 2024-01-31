import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

/**
 * This class represents a Task with a deadline.
 */
public class Deadline extends Task {

    private static final String TYPE_SYMBOL = "[D]";
    private LocalDate deadlineDate = null;
    private LocalTime deadlineTime = null;

    /**
     * Constructs a new deadline Task with the specified description, and deadline.
     *
     * @param description Description of the deadline Task
     * @param deadline    Deadline of the Task in the format "YYYY-MM-DD (optional: HH:MM)"
     * @throws MeanDukeException if deadline is invalid
     */
    public Deadline(String description, String deadline) throws MeanDukeException {
        super(description, TYPE_SYMBOL);
        //Parses deadline
        String[] parsedDeadline = deadline.split(" ");
        try {
            this.deadlineDate = LocalDate.parse(parsedDeadline[0]);
            if (parsedDeadline.length > 1) {
                this.deadlineTime = LocalTime.parse(parsedDeadline[1]);
            }
        } catch (DateTimeParseException e) {
            throw new MeanDukeException("Usage: \"add deadline <description> /by yyyy-mm-dd (hh:mm)\"");
        }
    }

    /**
     * Constructs a new deadline Task with the specified description, completion status, and deadline.
     *
     * @param description  Description of the deadline Task
     * @param isDone       boolean value that determines if the initialised deadline Task is completed or not
     * @param deadlineDate Deadline date of the Task in format "YYYY-MM-DD"
     * @param deadlineTime Deadline time of the Task in format "HH:MM", or null.
     */
    public Deadline(String description, boolean isDone, LocalDate deadlineDate, LocalTime deadlineTime) {
        super(description, TYPE_SYMBOL, isDone);
        this.deadlineDate = deadlineDate;
        this.deadlineTime = deadlineTime;
    }

    public String saveString() {
        return "DEADLINE" + "\n"
                + super.saveString() + "\n"
                + this.deadlineDate
                + (this.deadlineTime == null ? "" : ";" + this.deadlineTime);
    }

    @Override
    public String toString() {
        String deadlineString = this.deadlineDate.getDayOfMonth() + " "
                + this.deadlineDate.getMonth().toString() + " "
                + this.deadlineDate.getYear()
                + (this.deadlineTime == null ? "" : " " + this.deadlineTime);
        return super.toString() + " (by: " + deadlineString + ")";
    }
}
