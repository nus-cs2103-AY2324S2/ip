package earl.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Class representing task of type deadline.
 */
public class Deadline extends Task {

    protected LocalDateTime by;

    private static final DateTimeFormatter DATETIME_FORMAT = DateTimeFormatter
            .ofPattern("dd/MM/yyyy HHmm");

    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDateTime.parse(by, DATETIME_FORMAT);;
    }

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
