package sleepy.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * This class is a type of task.
 *
 * @author kjw142857
 */
public class PlannedTask extends Task {
    private String precedingEvent;
    private LocalDate formattedDate;

    /**
     * Constructor for the PlannedTask class.
     *
     * @param description Description of the plan details.
     * @param precedingEvent EventTask that needs to take place before execution.
     */
    public PlannedTask(String description, String precedingEvent) {
        super(description);
        this.precedingEvent = precedingEvent;
        try {
            formattedDate = LocalDate.parse(precedingEvent);
        } catch (DateTimeParseException d) {
            formattedDate = null;
        }
    }

    /**
     * Returns the description of this plan.
     *
     * @return Description of this plan.
     */
    @Override
    public String getDescription() {
        String triggerDate = formattedDate == null
                ? precedingEvent : formattedDate.format(DATE_FORMAT);
        return "[P]" + super.getDescription() + " (after: " + triggerDate + ")";
    }

    /**
     * Returns the raw description of this plan (as it was added by the user).
     *
     * @return Raw description of this plan.
     */
    @Override
    public String getRawDescription() {
        String details = super.getDescription().substring(TASK_DESCRIPTION_OFFSET);
        return "plan " + details + "/after " + precedingEvent;
    }
}
