package alfred.task;

import alfred.util.TimeManager;

import java.time.LocalDateTime;

/**
 * Represents an event task in duke.Duke chat-bot.
 * An event task has both a start and an end time in addition to the properties inherited from Task.
 */
public class Event extends Task {
    private String start;
    private String end;

    /**
     * Constructs a new Event task with the specified name, start time, and end time.
     * The start and end times are parsed and formatted by the TimeManager.
     *
     * @param name The name of the event task.
     * @param start The start time for the event, in a format understood by TimeManager.parseTime.
     * @param end The end time for the event, in a format understood by TimeManager.parseTime.
     */
    public Event(String name, String start, String end) {
        super(name);
        this.start = TimeManager.parseTime(start);
        this.end = TimeManager.parseTime(end);
    }

    /**
     * Retrieves the type identifier for an event task.
     *
     * @return A string representing the type of the task, specifically "[E]" for events.
     */
    @Override
    public String getType(){
        return "[E]";
    }

    /**
     * Retrieves the formatted time for the event task.
     *
     * @return A string representing the start and end times, formatted as "(from: [start time] to: [end time])".
     */
    @Override
    public String getTime(){
        return "(from: " + start + " to: " + end + ")";
    }

    /**
     * Retrieves the raw start and end times for the event task.
     *
     * @return The start and end times as a string, in the format provided to the constructor, separated by " to: ".
     */
    @Override
    public String getRawTime(){
        return this.start + " to: " + this.end;
    }

    /**
     * Delays the event by 1 day.
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
     * Postpone the event by specified number of days.
     *
     * @param days The number of days to postpone the event.
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
     * Reschedules the event to new specified start and end dates.
     *
     * @param start The start end date to reschedule the event to.
     * @param end The new end date to reschedule the event to.
     */
    @Override
    public void reschedule(String start, String end){
        try {
            LocalDateTime newStartTime = TimeManager.convertTime(end);
            this.start = TimeManager.parseTime(String.valueOf(newStartTime));
            LocalDateTime newEndTime = TimeManager.convertTime(end);
            this.end = TimeManager.parseTime(String.valueOf(newEndTime));
        } catch (TaskException e) {
            this.end = end;
        }
    }
}
