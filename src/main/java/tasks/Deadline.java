package tasks;

import datesandtimes.DateTimeParser;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Represents a task with a deadline, extending the base Task class.
 */
public class Deadline extends Task {
    private LocalDate dateBy;
    private LocalTime timeBy;

    /**
     * Constructs a Deadline task with the specified task name, deadline date, and time.
     *
     * @param taskName The name of the task.
     * @param deadline The deadline date of the task in the "yyyy-MM-dd" format.
     * @param time     The deadline time of the task in the "HHmm" format.
     */
    public Deadline(String taskName, String deadline, String time) {
        super(taskName, "D");
        this.dateBy = LocalDate.parse(deadline);
        this.timeBy = DateTimeParser.parseTimeAsLocalTime(time);
        setTime(new String[]{time, "NA"});
        setDate(new String[]{deadline, "NA"});
    }

    /**
     * Constructs a Deadline task with the specified task name, deadline date, time, and task status.
     *
     * @param taskName   The name of the task.
     * @param deadline   The deadline date of the task in the "yyyy-MM-dd" format.
     * @param time       The deadline time of the task in the "HHmm" format.
     * @param isTaskDone The status of the task (0 for not done, 1 for done).
     */
    public Deadline(String taskName, String deadline, String time, int isTaskDone) {
        super(taskName, "D");
        assert (isTaskDone == 1 || isTaskDone == 0) : "Invalid isTaskDone feed!";
        this.dateBy = LocalDate.parse(deadline);
        this.timeBy = DateTimeParser.parseTimeAsLocalTime(time);
        changeStatus(isTaskDone);
        setTime(new String[]{time, "NA"});
        setDate(new String[]{deadline, "NA"});
    }

    /**
     * Returns a string representation of the Deadline task.
     *
     * @return A formatted string containing task details.
     */
    public String toString() {
        return "[D]" + "[" + this.getStatusIcon() + "] " + super.getTaskName()
                + " (by: " + DateTimeParser.parseDate(this.dateBy)
                + " " + DateTimeParser.parseTime(this.timeBy) + ")";
    }
}
