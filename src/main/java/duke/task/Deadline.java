package duke.task;

import java.time.LocalDateTime;
import java.util.Optional;

import duke.parser.DateHandler;
import duke.storage.SaveType;

/**
 * Deadline task to keep track of a task with one deadline.
 */
public class Deadline extends Task {

    //Legacy support
    protected String by = "";
    protected LocalDateTime byDateTime = null;

    /**
     * Creates a deadline task with string values.
     *
     * @param description Name of the task.
     * @param by          Deadline of the task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Creates a deadline task with a LocalDateTime object.
     *
     * @param description Name of the task.
     * @param by          Deadline of the task.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        byDateTime = by;
    }

    public Optional<LocalDateTime> getDateTime() {
        if (byDateTime != null) {
            return Optional.of(byDateTime);
        } else {
            return Optional.empty();
        }
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

    @Override
    public SaveType getType() {
        return SaveType.DEADLINE;
    }
}
