package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy");
    LocalDate deadlineDate;

    public Deadline(String description, String by) {
        super(description);
        deadlineDate = LocalDate.parse(by);
    }

    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        deadlineDate = LocalDate.parse(by);
    }

    /**
     * Returns the date in a new format when written in the save file.
     *
     * @param outputDate Date of the deadline object.
     * @return Formatted String date.
     */
    public String outputDateStringFormat(LocalDate outputDate) {
        return outputDate.format(OUTPUT_FORMAT);
    }

    /**
     * Returns the deadline in a new String format when written in a file.
     *
     * @return New String format.
     */
    @Override
    public String outputString() {
        return "D | " + super.outputString() + " | " + outputDateStringFormat(deadlineDate);
    }


    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + outputDateStringFormat(deadlineDate) + ")";
    }
}
