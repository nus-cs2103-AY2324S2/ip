package TaskList.Tasks;

import java.time.LocalDateTime;

import static utils.StringUtils.formatDateTime;

/**
 * Represents a deadline task.
 * A <code>Deadline</code> object corresponds to a task with a description and a deadline
 * e.g., <code>"deadline return book /by 2-12-2019 1800"</code>
 */
public class Deadline extends Task{
    LocalDateTime by;
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    public String toString() {
        return "[D]" + super.toString() + " (by: "+ formatDateTime(this.by)+")";
    }

    public String save() {
        return "deadline " + super.description + " /by " + formatDateTime(this.by);
    }
}