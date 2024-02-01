package henry.task;

import henry.HenryException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class Deadline extends Task {
    private final LocalDateTime date;
    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");

    /**
     * Creates a Deadline object.
     *
     * @param description The description of the deadline.
     * @param date The due date of the deadline.
     * @throws HenryException If the description or due date is not specified.
     */
    public Deadline(String description, String date) throws HenryException {
        super(description);

        if (date.isBlank()) {
            throw new HenryException("No due date specified!");
        }

        this.date = LocalDateTime.parse(date, INPUT_FORMATTER);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), date.format(OUTPUT_FORMATTER));
    }

    @Override
    public String toFileString() {
        return String.format("D | %s | %s", super.toFileString(), date.format(INPUT_FORMATTER));
    }
}
