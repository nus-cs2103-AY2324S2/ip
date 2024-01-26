import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public Event(String description, boolean isDone, LocalDateTime from, LocalDateTime to) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    @Override
    public void taskPrinter() {
        DateTimeFormatter output = DateTimeFormatter.ofPattern("dd MMMM yyyy hhmm a");
        String result = "    " + "[E][ ]" + " " + description + "(from: " + from.format(output) + " to: " + to.format(output) +")";
        System.out.println(result);
    }

    @Override
    public void taskPrinter(int index) {
        DateTimeFormatter output = DateTimeFormatter.ofPattern("dd MMMM yyyy hhmm a");
        String result = "    " + (index+1) + ".[E]" + getStatusIcon() + " " + description  + "(from: " + from.format(output) + " to: " + to.format(output) +")";
        System.out.println(result);
    }

    @Override
    public String storagePrinter() {
        DateTimeFormatter output = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
        return "E" + "|isdone" + (isDone ? 1 : 0) + "|desc" + description + "|from" + from.format(output) + "|to" + to.format(output);
    }
}
