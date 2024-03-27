package duke;

import java.time.LocalDateTime;

/**
 * Represents a task that has a timestamp
 */
interface TaskWithTime {
    /**
     * Returns a timestamp to compare the order of events.
     *
     * @return a LocalDateTime object to represent the timestamp of the task
     */
    LocalDateTime getTimestamp();
}
