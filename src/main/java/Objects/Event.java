package Objects;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Event class which inherits from tasks
 * which contains startDate and endDate
 */
public class Event extends Task {
    LocalDate startDate;
    LocalDate endDate;
    public Event(String name,boolean mark, LocalDate startDate, LocalDate endDate) {
        super(name, mark);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "[E] " + super.toString() + "(from : " + this.startDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " to: " + this.endDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    @Override
    public String toStringFile() {
        return "E|" + super.toStringFile() + "|" + this.startDate + "|" + this.endDate;
    }
}
