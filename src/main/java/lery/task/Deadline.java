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
     * Constructs a Deadline object with the specified description, extracting
     * the deadline date from the description.
     *
     * @param description the description of the deadline task.
     */
    public Deadline(String description) {
        super(description.split("/")[0]);
        this.deadline = LocalDate.parse(description.split("/by ")[1], formatter);
    }

    /**
     * Constructs a Deadline object with the specified event name and extra information.
     *
     * @param event      the event description of the deadline task.
     * @param extraInfo  the extra information containing the deadline date.
     */
    public Deadline(String event, String extraInfo) {
        super(event);
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




