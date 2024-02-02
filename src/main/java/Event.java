import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected LocalDate from;
    protected LocalDate to;

    public Event(String desc) {
        String[] splitDesc = desc.split(" /from ");
        this.description = splitDesc[0];
        String[] splitPeriod = splitDesc[1].split(" /to ");
        this.from = LocalDate.parse(splitPeriod[0]);
        this.to = LocalDate.parse(splitPeriod[1]);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " to: " + this.to.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}