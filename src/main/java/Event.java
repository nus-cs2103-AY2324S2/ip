import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    private DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");

    public Event(String description, String startTime, String endTime) {
        super(description);
        this.startTime = LocalDateTime.parse(startTime, inputFormat);
        this.endTime = LocalDateTime.parse(endTime, inputFormat);
    }

    @Override
    public String print() {
        String start = startTime.format(outputFormat);
        String end = endTime.format(outputFormat);
        String str = "[E]" + super.print() + "(from: " +
                start + "to: " + end + ")";
        return str;
    }
    @Override
    public String getDescription() {
        String start = startTime.format(outputFormat);
        String end = endTime.format(outputFormat);
        String str = "[E] " + super.getDescription() + " " + start + " " + end;
        return str;
    }

    @Override
    public String getTaskInfo() {
        String start = startTime.format(outputFormat);
        String end = endTime.format(outputFormat);
        return "[E] " + "/ [" + super.getStatusIcon() + "] / " + super.getTaskInfo() + " / " + start
                + " / " + end;
    }
}
