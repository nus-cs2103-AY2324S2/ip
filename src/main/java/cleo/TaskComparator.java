package cleo;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Comparator;

/**
 * Implements a custom Comparator for sorting Task objects. Provides specific rules for
 * prioritizing and comparing different types of tasks (Todo, Deadline, and Event).
 */
public class TaskComparator implements Comparator<Task> {

    /**
     * Compares two Task objects based on their type and relevant dates. Prioritizes tasks
     * in the following order:
     *  1. Upcoming Events and Deadlines (chronological order of their datetimes)
     *  2. Todos (in alphabetical order).
     *
     * @param t1 The first Task object to compare
     * @param t2 The second Task object to compare
     * @return A negative integer if t1 should come before t2, zero if they're 'equal' for sorting, a positive integer if t1 should come after t2
     */
    @Override
    public int compare(Task t1, Task t2) {
        // Same comparison logic as before
        if (t1 instanceof Todo && t2 instanceof Todo) {
            return t1.getDescription().compareTo(t2.getDescription()); // Alphabetical for Todo
        } else if (t1 instanceof Todo) {
            return 1; // Todo goes last
        } else if (t2 instanceof Todo) {
            return -1; // Todo goes last
        } else {
            // Both t1 and t2 are either Event or Deadline
            LocalDateTime t1DateTime = getDateTime(t1);
            LocalDateTime t2DateTime = getDateTime(t2);
            int dateTimeComparison = t1DateTime.compareTo(t2DateTime); // Chronological order
            if (dateTimeComparison == 0) {
                return t1.getDescription().compareTo(t2.getDescription()); // Alphabetical for same date
            } else {
                return dateTimeComparison;
            }
        }
    }

    /**
     * Helper method to extract a LocalDateTime representation for either Event or Deadline tasks.
     *
     * @param task The Task object (must be an Event or Deadline)
     * @return A LocalDateTime representing the start time of an Event or the deadline of a Deadline task
     */

    private LocalDateTime getDateTime(Task task) {
        if (task instanceof Event) {
            return LocalDateTime.of(((Event) task).getFrom(), LocalTime.MIN);
        } else {
            return ((Deadline) task).getBy();
        }
    }
}
