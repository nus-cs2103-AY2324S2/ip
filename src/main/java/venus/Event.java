package venus;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * This is an Event class that extends from the Task class.
 *
 * @author peterXGD
 * @since 2024-02-05
 */
public class Event extends Task {
    private LocalDate start;
    private LocalDate end;

    /**
     * Returns event given description, starting and closing date.
     * @param s Event string.
     * @param start Starting date in "YYYY-MM-DD" format.
     * @param end Ending date in "YYYY-MM-DD" format.
     */
    public Event(String s, String start, String end) {
        super(s);
        this.start = TimeFormatter.stringToTime(start.substring(4).trim());
        this.end = TimeFormatter.stringToTime(end.substring((2)).trim());
    }

    /**
     * Returns event class given description, task state, starting and closing date and dummy.
     *
     * @param s Event string
     * @param mark Mark if a task is completed
     * @param start Starting date in "YYYY-MM-DD" format.
     * @param end Ending date in "YYYY-MM-DD" format.
     * @param dummy Dummy boolean
     */
    public Event(String s, boolean mark, String start, String end, boolean dummy) {
        // constructor used for loading
        super(s);
        this.start = TimeFormatter.loadTimeFromString(start.trim());
        this.end = TimeFormatter.loadTimeFromString(end.trim());
        if (mark) {
            this.mark();
        } else {
            this.unmark();
        }
    }

    /**
     * Returns string that prints content of this class including starting and ending
     * dates in "MMM d yyyy" format.
     *
     * @return A string that includes day of event and the event itself.
     */
    @Override
    public String toString() {
        String x = (this.getMark()) ? "X" : " ";
        return "[E]" + "[" + x + "] " + this.getItem()
                + " (from: " + start.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " to: " + end.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
