package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task that needs to be done within a specified period.
 * Inherits from the Task class.
 */
public class DoWithinPeriodTask extends Task {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("MMM d yyyy");
    private LocalDate startDate;
    private LocalDate endDate;


    /**
     * Constructs a DoWithinPeriodTask object with the given description and period.
     * Parses the periodStart and periodEnd strings into LocalDate objects.
     * Throws IllegalArgumentException if the date format is invalid.
     *
     * @param description The description of the task.
     * @param periodStart The start date of the period in "MMM d yyyy" format.
     * @param periodEnd   The end date of the period in "MMM d yyyy" format.
     * @throws IllegalArgumentException If the date format is invalid.
     */
    public DoWithinPeriodTask(String description, String periodStart, String periodEnd) {
        super(description);
        try {
            this.startDate = LocalDate.parse(periodStart, FORMATTER);
            this.endDate = LocalDate.parse(periodEnd, FORMATTER);
        } catch (DateTimeParseException e) {
            System.err.println("Error parsing dates: " + e.getMessage());
            throw new IllegalArgumentException("Invalid date format. Please use 'MMM d yyyy'.");
        }
    }

    /**
     * Converts the DoWithinPeriodTask object to a string format suitable for saving to a file.
     *
     * @return The string representation of the task in file format.
     */
    @Override
    public String toFileFormat() {
        return String.format("P | %d | %s | %s | %s",
            isComplete ? 1 : 0,
            description,
            startDate.format(FORMATTER),
            endDate.format(FORMATTER));
    }

    @Override
    public String toString() {
        return "[P]" + super.toString() + " (between: "
            + startDate.format(FORMATTER) + " and "
            + endDate.format(FORMATTER) + ")";
    }
}
