package sleepy.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * This class is a type of task.
 *
 * @author kjw142857
 */
public class Deadline extends Task {
    private String givenDeadline;

    private LocalDate formattedDate;

    /**
     * Constructor for the Deadline class.
     *
     * @param rawDescription Full description of the deadline, including labels and timing.
     * @param description Description of the deadline details.
     * @param givenDeadline When the given deadline is.
     */
    public Deadline(String rawDescription, String description, String givenDeadline) {
        super(rawDescription, description);
        this.givenDeadline = givenDeadline;
        try {
            formattedDate = LocalDate.parse(givenDeadline);
        } catch (DateTimeParseException d) {
            formattedDate = null;
        }
    }

    /**
     * Returns the description of this deadline.
     *
     * @return Description of this deadline.
     */
    @Override
    public String getDescription() {
        String deadlineDate = formattedDate == null ?
                givenDeadline : formattedDate.format(DateTimeFormatter.ofPattern("d MMM yyyy"));
        return "[D]" + super.getDescription() + " (by: " + deadlineDate + ")";
    }
}
