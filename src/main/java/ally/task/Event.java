package ally.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Abstraction of Event Tasks.
 * Child class of Task.
 */
public class Event extends Task {

    protected LocalDate from;
    protected LocalDate to;

    /**
     * Public constructor to create Event
     * @param desc input required in the form of {@code name /from YYYY-MM-DD /to YYYY-MM-DD}
     */
    public Event(String desc) {
        String[] splitDesc = desc.split(" /from ");
        this.description = splitDesc[0];
        String[] splitPeriod = splitDesc[1].split(" /to ");
        this.from = LocalDate.parse(splitPeriod[0]);
        this.to = LocalDate.parse(splitPeriod[1]);
    }

    /**
     * Get String Representation of Event
     * @return String
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                    + " to: " + this.to.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
