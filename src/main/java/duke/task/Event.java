package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Class representing Events
 */
public class Event extends Task {
    protected String from;
    protected String to;

    protected LocalDate fromDate;
    protected LocalDate toDate;

    /**
     * Creates an instance of Event
     * @param name name of the event
     * @param from Start date/time of the event
     * @param to End date/time of the event
     */
    public Event(String name, String from, String to) {
        super(name);
        this.from = from;
        this.to = to;

        assert !name.equals("") : "name should not be empty";
        assert !from.equals("") : "from date should not be empty";
        assert !to.equals("") : "to date should not be empty";

        // Check if they are in dateTime format
        // If so, update the string
        try {
            fromDate = LocalDate.parse(from);
            this.from = fromDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));

        } catch (DateTimeParseException e) {}

        try {
            toDate = LocalDate.parse(to);
            this.to = toDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } catch (DateTimeParseException e) {}

    }

    @Override
    public String fileString() {
        return "E | " + super.fileString() + " | " + this.from + " | " + this.to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from + " to: " + this.to + ")";
    }
}
