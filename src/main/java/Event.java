import javax.swing.text.DateFormatter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task{

    protected LocalDate from;
    protected LocalDate to;
    public Event(String description, String from, String to) {
        super(description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.from = LocalDate.parse(from, formatter);
        this.to = LocalDate.parse(to, formatter);
    }


    @Override
    public String getTaskIcon() {
        return "E";
    }
    @Override
    public String ToString() {
        return super.ToString() + " (from: " + from.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " to: " + to.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
    @Override
    public String toStore() {
        if (isDone) {
            return getTaskIcon() + "/" + "1" + "/" + description + "/ " + from + "/ " + to;
        } else {
            return getTaskIcon() + "/" + "0" + "/" + description + "/ " + from + "/ " + to;
        }
    }
}
