package alfred.task;

import alfred.util.TimeManager;

/**
 * Represents a deadline task in duke.Duke chat-bot.
 * A deadline task has an end time in addition to the properties inherited from Task.
 */
public class Deadline extends Task {

    private String end;
    private final int NO_DAYS_ADDED = 0;

    /**
     * Constructs a new Deadline task with the specified name and end time.
     * The end time is parsed and formatted by the TimeManager.
     *
     * @param name The name of the deadline task.
     * @param end The end time for the deadline, in a format understood by TimeManager.parseTime.
     */
    public Deadline(String name, String end) {
        super(name);
        this.end = TimeManager.parseTime(end);
    }

    /**
     * Retrieves the type identifier for a deadline task.
     *
     * @return A string representing the type of the task, specifically "[D]" for deadlines.
     */
    @Override
    public String getType(){
        return "[D]";
    }

    /**
     * Retrieves the formatted time for the deadline task.
     *
     * @return A string representing the end time, formatted as "(by: [end time])".
     */
    @Override
    public String getTime(){
        return "(by: " + end + ")";
    }

    /**
     * Retrieves the raw end time for the deadline task.
     *
     * @return The end time as a string, in the format provided to the constructor.
     */
    @Override
    public String getRawTime(){
        return this.end;
    }

    /**
     * Delays the deadline by 1 day.
     *
     */
    @Override
    public void snooze() {
        try {
            String newTime = TimeManager.addDays(this.end, 1);
            this.end = newTime;
        } catch (TaskException e) {
            this.end = "Day after " + this.end;
        }
    }

    /**
     * Postpone the deadline by specified number of days.
     *
     * @param days The number of days to postpone the deadline.
     */
    @Override
    public void postpone(int days) {
        try {
            String newTime = TimeManager.addDays(this.end, days);
            this.end = newTime;
        } catch (TaskException e) {
            this.end = "Day after " + this.end;
        }
    }

    /**
     * Reschedules the deadline to a specified date.
     *
     * @param end The new date to reschedule the deadline to.
     */
    @Override
    public void reschedule(String end){
        try {
            String newTime = TimeManager.addDays(end, NO_DAYS_ADDED);
            this.end = newTime;
        } catch (TaskException e) {
            this.end = end;
        }
    }
}
