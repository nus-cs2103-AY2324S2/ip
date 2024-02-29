package bentley;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The Event class represents a task with a specific start and end date/time.
 * It extends the Task class and includes functionality to handle events.
 */
public class Event extends Task {

    private LocalDateTime dateTimeFrom;
    private LocalDateTime dateTimeTo;

    /**
     * Constructs an Event object with the given description, start date/time, and
     * end date/time.
     *
     * @param description  The description of the event.
     * @param dateTimeFrom The date and time when the event starts.
     * @param dateTimeTo   The date and time when the event ends.
     */
    public Event(String description, LocalDateTime dateTimeFrom, LocalDateTime dateTimeTo) {
        super(" (E) " + description + " | From " +
                dateTimeFrom.format(DateTimeFormatter.ofPattern("MMM d yyyy h:mma")) +
                " to " + dateTimeTo.format(DateTimeFormatter.ofPattern("MMM d yyyy h:mma")));
        this.dateTimeFrom = dateTimeFrom;
        this.dateTimeTo = dateTimeTo;
    }

    /**
     * Overrides the doneOrNot method in the Task class to provide specific logic
     * for events.
     *
     * @return The status of the event, indicating whether it is done or not.
     */
    @Override
    public String doneOrNot() {
        // Implement the logic to determine if the event is done or not
        // You might need to access a field like 'isDone' from the superclass
        return (isDone ? "X" : "");
    }

    /**
     * Returns a string representation of the Event object.
     * Overrides the toString method in the Task class.
     *
     * @return A formatted string containing the event description, start date/time,
     *         and end date/time.
     */
    @Override
    public String toString() {
        return super.toString();
    }

}
