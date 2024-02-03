package Duke.Tasks;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represent an event task.
 */
public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Constructor for the event class
     *
     * @param description Description of the event.
     * @param from Start date of the event.
     * @param to End date of the event.
     */
    public Event(String description, String from, String to) throws DateTimeException {
        super(description, 'E');
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        LocalDateTime fromParsed = LocalDateTime.parse(from.trim(), formatter);
        LocalDateTime toParsed = LocalDateTime.parse(to.trim(), formatter);
        this.from = fromParsed;
        this.to = toParsed;
    }

    /**
     * Convert from date from LocalDateTime to String
     *
     * @return String From date of the event.
     */
    public String fromDateInWords() {
        String dayWeek = from.getDayOfWeek().toString();
        int dayMonth = from.getDayOfMonth();
        String month = from.getMonth().toString();
        int year = from.getYear();
        return dayWeek + " " + dayMonth + " " + month + " " +year;
    }

    /**
     * Convert to date from LocalDateTime to String
     *
     * @return String To date of the event.
     */
    public String toDateInWords() {
        String dayWeek = to.getDayOfWeek().toString();
        int dayMonth = to.getDayOfMonth();
        String month = to.getMonth().toString();
        int year = to.getYear();
        return dayWeek + " " + dayMonth + " " + month + " " + year;
    }

    @Override
    public String toString() {
        String str = String.format(
                super.toString() + " (from %s to %s)", this.fromDateInWords(), this.toDateInWords());
        return str;
    }
}
