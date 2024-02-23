package riri;

import java.time.LocalDate;
/**
 * Class that represent tasks with deadlines
 */
public class Deadline extends Task {
    private LocalDate by;
    /**
     * Constructs a Deadline object representing a task with a specified completion deadline.
     *
     * This constructor creates a Deadline object with the given task description and the date
     * by which the task should be completed. The deadline is represented as a string, which
     * is parsed to extract the date information.
     *
     * @param task The task description provided by the user.
     * @param by   The deadline in string format representing the date the task should be completed by.
     *             The date is parsed internally to ensure accurate representation.
     *             The accepted date format is flexible, allowing natural language input.
     */
    public Deadline(String task, String by) {
        super(task);
        this.by = parseDate(by);
    }
    /**
     * Constructs a Deadline object representing a task with a specified completion deadline.
     * @param task The task description provided by the user.
     * @param by The deadline in LocalDate format representing the date the task should be completed by.
     */
    public Deadline(String task, LocalDate by) {
        super(task);
        this.by = by;
    }
    /**
     * Parses a formatted string to create a Deadline object.
     *
     * This static method is designed to create a Deadline object from a string formatted
     * in a specific way. The expected format is "[D][ ] task description (by: deadline)".
     * The method extracts relevant information such as task description, deadline, and completion
     * status to instantiate a Deadline object.
     *
     * @param inputString The formatted string to parse into a Deadline object.
     *                    It should follow the pattern "[D][ ] task description (by: deadline)".
     *
     * @return A Deadline object representing the parsed task with its completion status and deadline.
     *
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
        return "[D]" + super.toString() + " (by: " + stringifyDate(by) + ")";
    }
}
