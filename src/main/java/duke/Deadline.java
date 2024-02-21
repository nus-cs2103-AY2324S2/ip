package duke;

;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task.
 */
public class Deadline extends Task {

    protected LocalDate by;
    /**
     * Creates an deadline task.
     *
     * @param description Description of the task.
     * @param by due date of the task.
     */
    public Deadline(String description, String by) {
        super(description);

        this.by = LocalDate.parse(by, DateTimeFormatter.ofPattern(" yyyy-MM-dd"));
    }


    @Override
    public String getTaskIcon() {
        return "D";
    }
    @Override
    public String ToString() {
        return "[" + getTaskIcon() + "] " + "[" + getStatusIcon() + "] "+ description + " (by: "
                + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")" + " (" + getPriorityDataString() + ")";
    }

    @Override
    public String toStore() {
        if (isDone) {
            return getTaskIcon() + "/" + "1" + "/" + description + "/" + priority + "/ " + by;
        } else {
            return getTaskIcon() + "/" + "0" + "/" + description + "/" + priority + "/ " + by;
        }
    }
}
