package Duke.Activities;

import Duke.Commands.DateFormat;

import java.time.LocalDate;

/**
 * Represents an Event activity, which is a type of activity with a specific start and end date.
 */
public class Event extends Activity {

    /**
     * The start date of the Event activity.
     */
    private final LocalDate start;

    /**
     * The end date of the Event activity.
     */
    private final LocalDate end;

    /**
     * Constructor to initialize an Event activity with a specified name, start date, and end date.
     *
     * @param name  The name of the Event activity.
     * @param start The start date of the Event activity.
     * @param end   The end date of the Event activity.
     */
    public Event(String name, LocalDate start, LocalDate end) {
        super(name);
        this.start = start;
        this.end = end;
    }

    /**
     * Returns a formatted string representation of the Event activity.
     *
     * @return A string containing details of the Event activity, including name, completion state,
     *         start date, and end date.
     */
    @Override
    public String toString() {
        return "[E] " + super.getState() + " " + super.getName() +
                " (from: " + DateFormat.reformatDate(start) + "  to: " + DateFormat.reformatDate(end) + ")";
    }

    /**
     * Converts the Event activity to a storage-friendly string format.
     *
     * @return A string representing the Event activity in a storage-friendly format.
     */
    @Override
    public String toStorage() {
        String str = "event ";
        str += super.getName();
        str += " /from " + start + " /to " + end;
        if (super.isMarked) {
            str += " /isMarked 1";
        } else {
            str += " /isMarked 0";
        }
        return str;
    }
}
