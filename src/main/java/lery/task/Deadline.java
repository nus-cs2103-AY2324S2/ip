package lery.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task in the Lery chatbot application,
 * which is a specific type of task associated with a deadline date.
 *
 * It extends the generic Task class and provides methods to get the task type,
 * extra information (shortened and full), and description. Additionally, it overrides
 * the getType, getExtraInfoShortened, and getExtraInfo methods to customize the behavior
 * for a deadline task.
 */
public class Deadline extends Task {
    private static final String TYPE = "D";
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private LocalDate deadline;

    /**
     * Constructs a Deadline object with the specified event name and extra information.
     *
     * @param event      the event description of the deadline task.
     * @param extraInfo  the extra information containing the deadline date.
     * @param isDone     the completion status of the task (true if done, false otherwise).
     */
    public Deadline(String event, String extraInfo, boolean isDone) {
        super(event, isDone);
        this.deadline = LocalDate.parse(extraInfo.replace('/', '-'), formatter);;
    }

    /**
     * Gets the type of the deadline task.
     *
     * @return the task type ("D" for deadline).
     */
    @Override
    public String getType() {
        return this.TYPE;
    }

    /**
     * Gets a shortened version of extra information.
     *
     * @return a string containing the shortened form of the extra information.
     */
    @Override
    public String getExtraInfoShortened() {
        return this.deadline.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    /**
     * Gets the extra information of the deadline task.
     *
     * @return a string containing the extra information.
     */
    @Override
    public String getExtraInfo() {
        return "(by: " + this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    public LocalDate getDeadline() {
        return this.deadline;
    }

}




