package earl.tasks;

import java.time.LocalDateTime;

import earl.util.parsers.DateTimeParser;

/**
 * Class representing task of type deadline.
 */
public class Deadline extends Task {

    protected LocalDateTime by;

    /**
     * Class constructor.
     *
     * @param description  the task description
     * @param by           the date and time of the deadline
     */
    public Deadline(String description, String by) {
        super(description);
        taskType = TaskType.DEADLINE;
        this.by = DateTimeParser.parse(by);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)",
                super.toString(),
                DateTimeParser.dateTimeToString(by));
    }

    @Override
    public String toStorageString() {
        return String.format("%s,%s,%s,%s",
                taskType.toString(),
                super.getStatusIcon(),
                getDescription(),
                DateTimeParser.dateTimeToString(by));
    }
}
