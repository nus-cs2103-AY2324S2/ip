package fredricksen.tasks;

import fredricksen.exceptions.FredricksenException;

/**
 * Represents a "Deadline" class, which extends the Task base class.
 * A "Deadline" Task is a task that creates a "Deadline" object with the
 * task description, the task type, and the status of the task isDone,
 * represented by boolean values.
 */
public class Deadline extends Task {

    /**
     * Constructs a Deadline instance with the specified task description,
     * the task type, and the status of the task isDone, represented by boolean values.
     *
     * @param task The task description as per user input.
     * @param type The task type.
     * @param isDone The status of the task isDone, represented by boolean values.
     */
    public Deadline(String task, String type, boolean isDone) {
        super(task, type, isDone);
    }

    /**
     * Returns the String representation of a date in MM DD YYYY form eg. Dec 15 2020.
     * @param fullCommand The full command that the user input.
     * @return A String in the format MM DD YYYY form eg. Dec 15 2020.
     */
    public String formatByDtPattern(String fullCommand) {
        int byStartIndex = fullCommand.indexOf("/by");
        String byDate = fullCommand.substring(byStartIndex + 4);
        String formattedDate;
        try {
            formattedDate = this.formatDates(byDate);
        } catch (FredricksenException err) {
            formattedDate = "Invalid Date";
        }
        return formattedDate;
    }

    /**
     * Get the description of the task from user input command.
     *
     * @param fullCommand The full command that the user input.
     * @return A String of the description of the command.
     */
    public String getDescription(String fullCommand) {
        int descIndex = fullCommand.indexOf("/by");
        return fullCommand.substring(9, descIndex - 1);
    }

    /**
     * Format the Date from the task description.
     *
     * @return A String that is formatted with the description plus formatted date.
     */
    public String formatByDateString() {
        String formattedByDate = formatByDtPattern(this.getTask());

        if (formattedByDate.equals("Invalid Date")) {
            return "Invalid Date";
        } else {
            return getDescription(this.getTask())
                    + " (by: " + formattedByDate
                    + ")";
        }
    }

    @Override
    public String toString() {
        if (formatByDateString().equals("Invalid Date")) {
            return "Invalid Date";
        }
        return super.toString() + formatByDateString();
    }
}
