package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate byDate;
    protected String by;
    public Deadline(String task, String by) {
        super(task);
        this.byDate = parseDate(by);
        this.by = byDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }
    public Deadline(String task, LocalDate by) {
        super(task);
        this.byDate = by;
        this.by = byDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }
    private LocalDate parseDate(String date) {
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("M/d/yyyy HHmm");
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("M/d/yyyy");
        DateTimeFormatter formatter3 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter formatter4 = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        DateTimeFormatter[] formatters = {formatter1, formatter2, formatter3};

        for (DateTimeFormatter dateFormat : formatters) {
            try {
                return LocalDate.parse(date, dateFormat);
            } catch (IllegalArgumentException e) {
                // Parsing failed for this pattern, try the next one
            }
        }

        throw new IllegalArgumentException("Unable to parse the date string using any of the specified patterns.");
    }
    /** Static method to create a Deadline object from a formatted string
     * @param inputString string to parse to Deadline object
     * @return Deadline object
     */
    public static Deadline parseDeadlineFromString(String inputString) {
        // Assuming the inputString is formatted as "[D][ ] task description (by: deadline)"
        int indexOfOpeningBracket = inputString.indexOf('[');
        int indexOfClosingBracket = inputString.indexOf(']');
        int indexOfBy = inputString.indexOf("(by:");

        char status = inputString.charAt(4);
        String taskDescription = inputString.substring(indexOfClosingBracket + 4, indexOfBy).trim();
        String by = inputString.substring(indexOfBy + 4, inputString.length() - 1).trim();

        Deadline deadline = new Deadline(taskDescription, by);

        // Check the status and mark the Deadline as done if needed
        if (status == 'X') {
            deadline.markDone();
        }
        return deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}