package tasks;

import java.time.LocalDate;
import java.time.LocalTime;

import datesandtimes.*;

public class Events extends Task {
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalTime startTime;
    private LocalTime endTime;

    public Events(String taskName, String dateFrom, String timeFrom, String dateTo, String timeTo) {
        super(taskName, "E");
        this.startDate = LocalDate.parse(dateFrom);
        this.endDate = LocalDate.parse(dateTo);
        this.startTime = DateTimeParser.parseTimeAsLocalTime(timeFrom);
        this.endTime = DateTimeParser.parseTimeAsLocalTime(timeTo);
        setDate(new String[] {dateFrom, dateTo});
        setTime(new String[] {timeFrom, timeTo});

    }

    public Events(String taskName, String dateFrom, String timeFrom, String dateTo, String timeTo, int isTaskDone) {
        super(taskName, "E");
        changeStatus(isTaskDone);
        this.startDate = LocalDate.parse(dateFrom);
        this.endDate = LocalDate.parse(dateTo);
        this.startTime = DateTimeParser.parseTimeAsLocalTime(timeFrom);
        this.endTime = DateTimeParser.parseTimeAsLocalTime(timeTo);
        setDate(new String[] {dateFrom, dateTo});
        setTime(new String[] {timeFrom, timeTo});
    }

    public String toString() {
        return "[E]" + "[" + getStatusIcon() + "] " + getTaskName()
                + " (from: "
                + DateTimeParser.parseDate(this.startDate) + " " + DateTimeParser.parseTime(this.startTime)
                + " to: "
                + DateTimeParser.parseDate(this.endDate) + " " + DateTimeParser.parseTime(this.endTime) + ")";
    }
}
