package wis.task;

import java.time.LocalTime;
import java.time.format.TextStyle;
import java.util.Locale;
import java.time.LocalDateTime;

/**
 * An event task that has starting and ending dates and time. A description of
 * the task is inherited from Task class.
 */
public class Event extends Task{
    private LocalDateTime beginTime;
    private LocalDateTime endTime;
    public Event(String description, LocalDateTime beginTime, LocalDateTime endTime) {
        super(description);
        this.beginTime = beginTime;
        this.endTime = endTime;
    }

    /**
     * {@inheritDoc}
     *
     * Date and time are displayed in the format: Feb 15 2024, 1800
     */
    @Override
    public String toString() {
        return String.format("[E]%s (from: %s %d %d, %s to: %s %d %d, %s)",
                super.toString(),
                beginTime.getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH).toUpperCase(),
                beginTime.getDayOfMonth(),
                beginTime.getYear(),
                LocalTime.of(beginTime.getHour(), beginTime.getMinute()),
                endTime.getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH).toUpperCase(),
                endTime.getDayOfMonth(),
                endTime.getYear(),
                LocalTime.of(endTime.getHour(), endTime.getMinute()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toSavedString() {
        return String.format("E#!#%s#!#%s#!#%s\n",
                super.toSavedString(),
                beginTime.toString(),
                endTime.toString());
    }
}
