package earl.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Class representing task of type deadline.
 */
public class Deadline extends Task {

    private static final DateTimeFormatter DATETIME_FORMAT = DateTimeFormatter
            .ofPattern("dd/MM/yyyy HHmm");

    protected LocalDateTime by;

    /**
     * Class constructor.
     *
     * @param description  the task description
     * @param by           the date and time of the deadline
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDateTime.parse(by, DATETIME_FORMAT);;
    }

    /**
     * Class constructor with specified status.
     *
     * @param status       the completion status
     * @param description  the task description
     * @param by           the date and time of the deadline
     */
    public Deadline(String status, String description, String by) {
        super(status, description);
        this.by = LocalDateTime.parse(by, DATETIME_FORMAT);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(),
                by.format(DATETIME_FORMAT));
    }

    @Override
    public String toStorageString() {
        return String.format("D,%s,%s,%s", super.getStatusIcon(),
                description, by.format(DATETIME_FORMAT));
    }
}
