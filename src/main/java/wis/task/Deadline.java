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
    private LocalDateTime dueDateTime;

    public Deadline(String description, LocalDateTime dueDateTime) {
        super(description);
        this.dueDateTime = dueDateTime;
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
                dueDateTime.getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH).toUpperCase(),
                dueDateTime.getDayOfMonth(),
                dueDateTime.getYear(),
                LocalTime.of(dueDateTime.getHour(), dueDateTime.getMinute()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toSavedString() {
        return String.format("D#!#%s#!#%s\n",
                super.toSavedString(),
                dueDateTime.toString());
    }
}
