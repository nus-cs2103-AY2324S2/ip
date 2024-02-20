package duke;

import java.time.LocalDateTime;

/**
 * Represents a recurring task.
 */
public interface RecurringTask {
    String makeRecur();

    /**
     * Checks if the task is recurring.
     *
     * @return True if the task is recurring, false otherwise.
     */
    boolean isRecurring();

    /**
     * Adjusts the date for a recurring task.
     */
    void adjustDeadline();

    LocalDateTime getEndDateTime();
}
