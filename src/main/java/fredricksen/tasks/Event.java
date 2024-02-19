package fredricksen.tasks;

import fredricksen.exceptions.FredricksenException;

/**
 * Represents an "Event" class, which extends the Task base class.
 * An "Event" Task is a task that creates an "Event" object with the
 * task description, the task type, and the status of the task isDone,
 * represented by boolean values.
 */
public class Event extends Task {
    /**
     * Constructs an Event instance with the specified task description,
     * the task type, and the status of the task isDone, represented by boolean values.
     *
     * @param task The task description as per user input.
     * @param type The task type.
     * @param isDone The status of the task isDone, represented by boolean values.
     */
    public Event(String task, String type, boolean isDone) {
        super(task, type, isDone);
    }

    /**
     * Formats the "from" start date in the format specified by
     * the formatDates function.
     *
     * @param fullCommand The full command that the user input.
     * @return A String with the formatted date or date time.
     */
    public String formatFromDtPattern(String fullCommand) {
        int fromStartIndex = fullCommand.indexOf("/from");
        int toStartIndex = fullCommand.indexOf("/to");

        assert fromStartIndex != -1 : "Should not happen";
        assert toStartIndex != -1 : "Should not happen";

        String fromDate = fullCommand.substring(fromStartIndex + 6, toStartIndex - 1);

        String formattedDate;
        try {
            formattedDate = this.formatDates(fromDate);
        } catch (FredricksenException err) {
            formattedDate = "Invalid Date";
        }
        return formattedDate;
    }

    /**
     * Formats the "to" end date in the format specified by
     * the formatDates function.
     *
     * @param fullCommand The full command that the user input.
     * @return A String with the formatted date or date time.
     */
    public String formatToDtPattern(String fullCommand) {
        int toStartIndex = fullCommand.indexOf("/to");
        String toDate = fullCommand.substring(toStartIndex + 4);
        String formattedDate;
        try {
            formattedDate = this.formatDates(toDate);
        } catch (FredricksenException err) {
            formattedDate = "Invalid Date";
        }
        return formattedDate;
    }

    public String getDescription(String fullCommand) {
        int descIndex = fullCommand.indexOf("/from");
        return fullCommand.substring(6, descIndex - 1);
    }

    /**
     * Format both the "from" and "to" date from the task description.
     * Then format and combine the task description and the dates together.
     *
     * @return A String that is formatted with the task description plus formatted dates.
     */
    public String formatStringOutput() {
        String formattedFromDate = formatFromDtPattern(this.getTask());
        String formattedToDate = formatToDtPattern(this.getTask());

        if (formattedFromDate.equals("Invalid Date") || formattedToDate.equals("Invalid Date")) {
            return "Invalid Date";
        } else {
            return getDescription(this.getTask())
                    + " (from: " + formattedFromDate
                    + " to: " + formattedToDate
                    + ")";
        }
    }

    @Override
    public String toString() {
        if (formatStringOutput().equals("Invalid Date")) {
            return "Invalid Date";
        }
        return super.toString() + formatStringOutput();
    }
}

