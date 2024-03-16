package ellie.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an event task with a specific start and end date.
 * Example: event team project meeting /from 2022-02-15 /to 2022-02-16
 * (a) event team project meeting /from 2-10-2019 /to 2-4pm
 * (b) event orientation week /from 4-10-2019 /to 11-10-2019
 */
public class Event extends Task {

    private String startDateString;
    private String endDateString;

    private LocalDate startDate;
    private LocalDate endDate;


    /**
     * Constructs an Event object with the given description, start date, and end date.
     *
     * @param description     The description of the event.
     * @param startDateString The start date of the event in string format.
     * @param endDateString   The end date of the event in string format.
     */
    public Event(String description, String startDateString, String endDateString) {
        super(description);
        this.startDateString = startDateString;
        this.endDateString = endDateString;

        try {
            startDate = LocalDate.parse(startDateString);
            endDate = LocalDate.parse(endDateString);
        } catch (DateTimeParseException e) {
            startDate = null;
            endDate = null;
        }

    }

    /**
     * Constructs an Event object with the given description, completion status, start date, and end date.
     *
     * @param description     The description of the event.
     * @param isDone          The completion status of the event (1 for done, 0 for not done).
     * @param startDateString The start date of the event in string format.
     * @param endDateString   The end date of the event in string format.
     */
    public Event(String description, int isDone, String startDateString, String endDateString) {
        super(description, isDone);
        this.startDateString = startDateString;
        this.endDateString = endDateString;

        try {
            startDate = LocalDate.parse(startDateString);
            endDate = LocalDate.parse(endDateString);
        } catch (DateTimeParseException e) {
            startDate = null;
            endDate = null;
        }
    }

    /**
     * Returns the task type identifier for an event.
     *
     * @return The task type identifier ('E' for event).
     */
    @Override
    public char getTaskType() {
        return 'E';
    }

    /**
     * Returns a formatted string representation of the event task.
     * Includes the task type, completion status, description, and event duration.
     *
     * @return A formatted string representation of the event task.
     */
    @Override
    public String listTaskString() {
        if (startDate == null) {
            return "[E]" + super.listTaskString()
                    + " (from: " + startDateString
                    + " to: " + endDateString + ")";
        } else {
            return "[E]" + super.listTaskString()
                    + " (from: " + startDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                    + " to: " + endDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
        }
    }

    /**
     * Returns the start date of the event in string format.
     *
     * @return The start date of the event.
     */
    public String getStartDate() {
        return this.startDateString;
    }

    /**
     * Returns the end date of the event in string format.
     *
     * @return The end date of the event.
     */
    public String getEndDate() {
        return this.endDateString;
    }


}
