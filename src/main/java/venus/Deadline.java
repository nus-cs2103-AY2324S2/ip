package venus;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * This is a deadline class that extends from the Task class.
 *
 * @author peterXGD
 * @since 2024-02-05
 */
public class Deadline extends Task {
    private LocalDate deadline;

    /**
     * Returns deadline task with event and deadline as parameter.
     * @param s The deadline event itself
     * @param deadline The time string used for the deadline in "YYYY-MM-DD" format.
     */
    public Deadline(String s, String deadline) {
        super(s);
        this.deadline = TimeFormatter.stringToTime(deadline);
    }

    /**
     * Returns deadline task with event and deadline as parameter.
     * @param s The deadline event itself
     * @param deadline The time string used for the deadline in "YYYY-MM-DD" format.
     */
    public Deadline(String s, boolean mark, String deadline) throws DateTimeParseException {
        //constructor used for loading
        super(s);
        this.deadline = TimeFormatter.loadTimeFromString(deadline);
        if (mark) {
            this.mark();
        } else {
            this.unmark();
        }
    }

    /**
     * Return string that prints content of this class with time of format
     * "MMM d yyyy".
     *
     * @return A string that includes day of deadline and the task itself.
     */
    @Override
    public String toString() {
        String x = (this.getMark()) ? "X" : " ";
        return "[D]" + "[" + x + "] " + this.getItem()
                + " (by: " + deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
