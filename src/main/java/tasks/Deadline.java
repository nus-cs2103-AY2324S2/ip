package tasks;

import datesandtimes.DateTimeParser;
import java.time.LocalDate;
import java.time.LocalTime;

public class Deadline extends Task {
    private LocalDate dateBy;
    private LocalTime timeBy;
    public Deadline(String taskName, String deadline, String time) {
        super(taskName, "D");
        this.dateBy = LocalDate.parse(deadline);
        this.timeBy = DateTimeParser.parseTimeAsLocalTime(time);
        setTime(new String[] {time, "NA"});
        setDate(new String[] {deadline, "NA"});
    }

    public Deadline(String taskName, String deadline, String time, int isTaskDone) {
        super(taskName, "D");
        this.dateBy = LocalDate.parse(deadline);
        this.timeBy = DateTimeParser.parseTimeAsLocalTime(time);
        changeStatus(isTaskDone);
        setTime(new String[] {time, "NA"});
        setDate(new String[] {deadline, "NA"});
    }

    public String toString() {
        return "[D]" + "[" + this.getStatusIcon() + "] " + super.getTaskName()
                + " (by: " + DateTimeParser.parseDate(this.dateBy)
                + " " + DateTimeParser.parseTime(this.timeBy) + ")";
    }
}
