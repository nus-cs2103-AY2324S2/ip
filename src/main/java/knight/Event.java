package knight;

import java.time.LocalDate;

public class Event extends Task {
    private LocalDate startTime;
    private LocalDate endTime;
    Event(String name, String startTime, String endTime) {
        super(name);
        this.startTime = LocalDate.parse(startTime);
        this.endTime = LocalDate.parse(endTime);
    }

    String getCommand() {
        return "event " + name + " /from " + startTime + " /to " + endTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + startTime.format(Task.DATE_FORMATTER)
                + " to: " + endTime.format(Task.DATE_FORMATTER) + ")";
    }
}
