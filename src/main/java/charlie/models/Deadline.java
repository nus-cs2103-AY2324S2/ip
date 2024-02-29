package charlie.models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class Deadline extends Task {
    protected LocalDate by;
    protected String byString;

    /**
     * constructor for task Deadline
     * @param description description of task deadline in string form
     * @param byString time by which the task deadline is due, is in ISO_LOCAL_DATE format (yyyy-MM-dd)
     */
    public Deadline(String description, Integer priorityNumber, String byString) {
        super(description, priorityNumber);

        // Assert that description is not null or empty
        assert description != null && !description.trim().isEmpty() : "Description cannot be null or empty";
        // Assert that byString is not null
        assert byString != null : "byString cannot be null";
        // handle the case where it's not a date

        String datePattern = "\\d{4}-\\d{2}-\\d{2}";
        if (byString.matches(datePattern)) {
            this.by = LocalDate.parse(byString); // Assumes input is in ISO_LOCAL_DATE format (yyyy-MM-dd)
            assert this.by != null : "Date parsing failed when it should have succeeded";
        } else {
            this.byString = byString;
        }
    }

    /**
     * @return description string for deadline event
     */
    @Override
    public String toString() {
        if (by != null) {
            // `by` is set, so format it using DateTimeFormatter
            return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
        } else {
            // `by` is not set, meaning `byString` did not represent a date in the expected format
            // In this case, simply return the `byString` without attempting to format it as a date
            return "[D]" + super.toString() + " (by: " + byString + ")";
        }
    }
    public String getBy() {
        return this.byString;
    }
}


