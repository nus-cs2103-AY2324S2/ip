import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    protected LocalDateTime from;
    protected LocalDateTime to;
    public Event(String description, String from, String to){
        super(description, 'E');
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        LocalDateTime fromParsed = LocalDateTime.parse(from.trim(), formatter);
        LocalDateTime toParsed = LocalDateTime.parse(to.trim(), formatter);
        this.from = fromParsed;
        this.to = toParsed;
    }
    public String fromDateInWords(){
        String dayWeek = from.getDayOfWeek().toString();
        int dayMonth = from.getDayOfMonth();
        String month = from.getMonth().toString();
        int year = from.getYear();
        return dayWeek + " " + dayMonth + " " + month + " " +year;
    }
    public String toDateInWords(){
        String dayWeek = to.getDayOfWeek().toString();
        int dayMonth = to.getDayOfMonth();
        String month = to.getMonth().toString();
        int year = to.getYear();
        return dayWeek + " " + dayMonth + " " + month + " " +year;
    }
    @Override
    public String toString(){
        String str = String.format(
                super.toString() + " (from %s to %s)", this.fromDateInWords(), this.toDateInWords());
        return str;
    }
}
