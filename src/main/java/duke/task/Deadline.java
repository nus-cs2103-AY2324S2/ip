package duke.task;

import duke.parser.DateHandler;

import java.time.LocalDateTime;

/**
 * Deadline task to keep track of a task with one deadline.
 */
public class Deadline extends Task {

    //Legacy support
    protected String by = "";
    protected LocalDateTime byDateTime = null;

    /**
     * Legacy version to create a deadline task with string values only.
     *
     * @param description Name of the task.
     * @param by          Deadline of the task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Create a deadline task with a LocalDateTime object.
     *
     * @param description Name of the task.
     * @param by          Deadline of the task.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        byDateTime = by;
    }


    @Override
    public String saveFile() {
        if (byDateTime == null) {
            return "D" + "|" + super.done() + "|" + super.description + "|" + by + "|" + "null";
        } else {
            return "D" + "|" + super.done() + "|" + super.description + "|" + by + "|" + byDateTime.toString();
        }
    }

    @Override
    public String toString() {
        if (byDateTime == null) {
            return "[D]" + super.toString() + " (by: " + by + ")";
        } else {
            return "[D]" + super.toString() + " (by: " + DateHandler.formatDate(byDateTime) + ")";
        }
    }
}