package lite.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private String start;
    private String end;
    private LocalDateTime from;
    private LocalDateTime to;


    public Event(String description, String start, String end) {
        super(description);
        this.start = start.trim();
        this.end = end.trim();
        String startTime = start.split("from ")[1].trim();
        String endTime = end.split("to ")[1].trim();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        this.from = LocalDateTime.parse(startTime, formatter);
        this.to = LocalDateTime.parse(endTime, formatter);
    }

    /**
     * {@inheritDoc}
     *
     * Date is displayed in format: (from: Monday 2pm to: Tuesday 4pm)
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.getMonth() + " "
                + from.getDayOfMonth() + " " + from.getYear() + " "
                + from.getHour() + ":" + from.getMinute() + " to: " +
                to.getMonth() + " " + to.getDayOfMonth() + " " + to.getYear() + " "
                + to.getHour() + ":" + to.getMinute() + ")";
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public String saveToFile() {
        return "E!" + super.saveToFile() + "!" + this.start + "!" + this.end + "\n";
    }
}
