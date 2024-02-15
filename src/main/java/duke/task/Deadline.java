package duke.task;

import java.time.LocalDateTime;

/**
 * Represents a Deadline task that stores the description, due date (i.e., by) and status of completion.
 */
public class Deadline extends Task {

    protected LocalDateTime by;

    /**
     * Initlialises a Deadline with the description, due date and status of completion.
     * @param description deadline description
     * @param by due date for completion
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    @Override
    public String getDescription() {
        String strBy = convertDate(by);
        return "[D]" + super.getDescription() + "(by: " + strBy + ")";
    }

    @Override
    public LocalDateTime getStart() {
        return by;
    }
}
