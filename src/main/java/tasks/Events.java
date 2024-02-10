package tasks;

import java.time.LocalDate;
import java.time.LocalTime;

import datesandtimes.DateTimeParser;

/**
 * Represents an event task with a start and end date and time, extending the base Task class.
 */
public class Events extends Task {
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalTime startTime;
    private LocalTime endTime;

    /**
     * Constructs an Events task with the specified task name, start date, start time, end date, and end time.
     *
     * @param taskName The name of the task.
     * @param dateFrom The start date of the event in the "yyyy-MM-dd" format.
     * @param timeFrom The start time of the event in the "HHmm" format.
     * @param dateTo   The end date of the event in the "yyyy-MM-dd" format.
     * @param timeTo   The end time of the event in the "HHmm" format.
     */
    public Events(String taskName, String dateFrom, String timeFrom, String dateTo, String timeTo) {
        super(taskName, "E");
        this.startDate = LocalDate.parse(dateFrom);
        this.endDate = LocalDate.parse(dateTo);
        this.startTime = DateTimeParser.parseTimeAsLocalTime(timeFrom);
        this.endTime = DateTimeParser.parseTimeAsLocalTime(timeTo);
        setDate(new String[]{dateFrom, dateTo});
        setTime(new String[]{timeFrom, timeTo});
    }

    /**
     * Constructs an Events task with the specified task name, start date, start time,
     * end date, end time, and task status.
     *
     * @param taskName   The name of the task.
     * @param dateFrom   The start date of the event in the "yyyy-MM-dd" format.
     * @param timeFrom   The start time of the event in the "HHmm" format.
     * @param dateTo     The end date of the event in the "yyyy-MM-dd" format.
     * @param timeTo     The end time of the event in the "HHmm" format.
     * @param isTaskDone The status of the task (0 for not done, 1 for done).
     */
    public Events(String taskName, String dateFrom, String timeFrom, String dateTo, String timeTo, int isTaskDone) {
        super(taskName, "E");
        assert (isTaskDone == 1 || isTaskDone == 0) : "Invalid isTaskDone feed!";
        changeStatus(isTaskDone);
        this.startDate = LocalDate.parse(dateFrom);
        this.endDate = LocalDate.parse(dateTo);
        this.startTime = DateTimeParser.parseTimeAsLocalTime(timeFrom);
        this.endTime = DateTimeParser.parseTimeAsLocalTime(timeTo);
        setDate(new String[]{dateFrom, dateTo});
        setTime(new String[]{timeFrom, timeTo});
    }

    /**
     * Returns a string representation of the Events task.
     *
     * @return A formatted string containing task details.
     */
    public String toString() {
        return "[E]" + "[" + getStatusIcon() + "] " + getTaskName()
                + " (from: "
                + DateTimeParser.parseDate(this.startDate) + " " + DateTimeParser.parseTime(this.startTime)
                + " to: "
                + DateTimeParser.parseDate(this.endDate) + " " + DateTimeParser.parseTime(this.endTime) + ")";
    }
}
