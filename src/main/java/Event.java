import java.sql.Time;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class Event extends Task{
    private LocalDate start;
    private LocalDate end;
    public Event (String s, String start, String end){
        super(s);
        this.start = TimeFormatter.stringToTime(start.substring(4).trim());
        this.end = TimeFormatter.stringToTime(end.substring((2)).trim());
    }

    public Event (String s, boolean mark, String start, String end){
        super(s);
        this.start = TimeFormatter.loadTimeFromString(start.substring(4).trim());
        this.end = TimeFormatter.loadTimeFromString(end.substring((2)).trim());
        if (mark) {
            this.mark();
        } else {
            this.unmark();
        }
    }

    public Event (String s, boolean mark, String start, String end, boolean dummy){ //constructor used for loading
        super(s);
        this.start = TimeFormatter.loadTimeFromString(start.trim());
        this.end = TimeFormatter.loadTimeFromString(end.trim());
        if (mark) {
            this.mark();
        } else {
            this.unmark();
        }
    }
    @Override
    public String toString(){
        String X = this.getMark() ? "X" : " ";
        return "[E]"+"[" + X + "] " + this.getItem()
                + " (from: " + start.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " to: " + end.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
