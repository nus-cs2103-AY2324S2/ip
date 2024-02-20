package Duke.Activities;

import Duke.Commands.DateFormat;

import java.time.LocalDate;

/**
 * The {@code Event} class represents an event activity, implementing the {@link Activity} interface. It encapsulates
 * the status, name, start date and time, and end date and time of an event. This class is designed to manage events,
 * allowing for them to be printed, named, and marked as complete or incomplete. It ensures that the event's start
 * date and time are logically before its end date and time.
 */
public class Event extends Activity {
    private final LocalDate start;
    private final LocalDate end;
    public Event(String name, LocalDate start, LocalDate end) {
        super(name);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "[E] " + super.getState() + " " + super.getName() +
                " (from: " + DateFormat.reformatDate(start) + "  to: " + DateFormat.reformatDate(end) + ")";
    }

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
