package wis.task;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.TextStyle;
import java.util.Locale;

/**
 * A Deadline task that has a due date and time. A description of
 * the task is inherited from Task class.
 */
public class Deadline extends Task {
    private LocalDateTime time;

    public Deadline(String description, LocalDateTime time) {
        super(description);
        this.time = time;
    }

    /**
     * {@inheritDoc}
     *
     * Date and time are displayed in the format: Feb 15 2024, 1800
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s %d %d, %s)",
                super.toString(),
                time.getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH).toUpperCase(),
                time.getDayOfMonth(),
                time.getYear(),
                LocalTime.of(time.getHour(), time.getMinute()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toSavedString() {
        return String.format("D#!#%s#!#%s\n",
                super.toSavedString(),
                time.toString());
    }
}
