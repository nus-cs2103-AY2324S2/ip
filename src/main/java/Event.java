<<<<<<< Updated upstream
=======

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

>>>>>>> Stashed changes
public class Event extends Task{

    protected String from;
    protected String to;
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }


    @Override
    public String getTaskIcon() {
        return "E";
    }
    @Override
    public String ToString() {
        return super.ToString() + " (from: " + from + "to: " + to + ")";
    }
    @Override
    public String toStore() {
        if (isDone) {
            return getTaskIcon() + "/" + "1" + "/" + description + "/" + from + "/" + to;
        } else {
            return getTaskIcon() + "/" + "0" + "/" + description + "/" + from + "/" + to;
        }
    }
}
