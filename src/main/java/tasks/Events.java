package tasks;

import java.time.LocalDate;
import datesandtimes.*;

public class Events extends Task {
    private LocalDate startTime;
    private LocalDate endTime;

    public Events(String taskName, String start, String end) {
        super(taskName, "E");
        this.startTime = LocalDate.parse(start);
        this.endTime = LocalDate.parse(end);
        super.setTime(new String[] {start, end});
    }

    public Events(String taskName, String start, String end, int isTaskDone) {
        super(taskName, "E");
        this.startTime = LocalDate.parse(start);
        this.endTime = LocalDate.parse(end);
        super.changeStatus(isTaskDone);
        super.setTime(new String[] {start, end});
    }

    public String toString() {
        return "[E]" + "[" + this.getStatusIcon() + "] " + super.getTaskName() + " (from: " +
                DateTimeParser.parseDate(this.startTime) + " to: " + DateTimeParser.parseDate(this.endTime) + ")";
    }
}
