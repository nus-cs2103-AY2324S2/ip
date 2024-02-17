package sleepy.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * This class is a type of task.
 *
 * @author kjw142857
 */
public class DeadlineTask extends Task {
    private String givenDeadline;
    private LocalDate formattedDate;

    /**
     * Constructor for the DeadlineTask class.
     *
     * @param description Description of the deadline details.
     * @param givenDeadline When the given deadline is.
     */
    public DeadlineTask(String description, String givenDeadline) {
        super(description);
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
        String deadlineDate = formattedDate == null
                ? givenDeadline : formattedDate.format(DATE_FORMAT);
        return "[D]" + super.getDescription() + " (by: " + deadlineDate + ")";
    }

    /**
     * Returns the raw description of this deadline (as it was added by the user).
     *
     * @return Raw description of this deadline.
     */
    @Override
    public String getRawDescription() {
        String details = super.getDescription().substring(TASK_DESCRIPTION_OFFSET);
        return "deadline " + details + "/by " + givenDeadline;
    }
}
