package slaybot;

import java.time.LocalDateTime;
import java.util.Comparator;

import entity.Task;

/**
 * A comparator for Task objects that orders them based on their associated date and time.
 * This class is used to sort tasks in ascending order of their date and time. If a task does not
 * have an associated date and time (i.e., the date is {@code null}), it is considered greater than
 * a task with a date (i.e., tasks without a date are placed at the end of the sorted list).
 */
public class TaskComparator implements Comparator<Task> {
    /**
     * Compares two Task objects to determine their ordering with respect to each other.
     *
     * @param task1 the first task to be compared.
     * @param task2 the second task to be compared.
     * @return a negative integer, zero, or a positive integer as the first argument is less than,
     *         equal to, or greater than the second. Tasks with {@code null} dates are considered
     *         greater than tasks with non-null dates.
     */
    @Override
    public int compare(Task task1, Task task2) {
        LocalDateTime dateTime1 = task1.getDate();
        LocalDateTime dateTime2 = task2.getDate();

        if (dateTime1 == null && dateTime2 == null) {
            return 0; // Both are null, consider them equal
        }
        if (dateTime1 == null) {
            return 1; // Null is considered greater than non-null
        }
        if (dateTime2 == null) {
            return -1; // Non-null is considered greater than null
        }
        return dateTime1.compareTo(dateTime2);
    }
}
