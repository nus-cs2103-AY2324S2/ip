package duchess.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDate from;
    protected LocalDate to;

    /**
     * Create new Event object.
     *
     * @param description description of Event Task.
     * @param from starting date of Event Task in the format dd/MM/yyyy.
     * @param to ending date of Event Task in the format dd/MM/yyyy.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = LocalDate.parse(from, DateTimeFormatter.ofPattern("dd/MM/yyyy"));;
        this.to = LocalDate.parse(to, DateTimeFormatter.ofPattern("dd/MM/yyyy"));;
    }

    /**
     * Returns String representation of object.
     *
     * @return String.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + " to: " + to.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
