package task;

import java.util.Comparator;

/**
 * Represents a comparator to compare tasks based on their priority.
 * <p>
 * This class is used to compare tasks based on their priority. It provides a
 * method to compare two tasks based on their priority.
 * </p>
 */
public class PriorityComparator implements Comparator<Task> {
    /**
     * Compares two tasks based on their priority.
     * 
     * @param t1 The first task to be compared.
     * @param t2 The second task to be compared.
     * @return A negative integer, zero, or a positive integer as the first task has
     *         a higher, equal, or lower priority than the second task.
     */
    @Override
    public int compare(Task t1, Task t2) {
        if (t1.getPriority() == null && t2.getPriority() == null) {
            return 0;
        } else if (t1.getPriority() == null) {
            return 1;
        } else if (t2.getPriority() == null) {
            return -1;
        } else {
            return t1.getPriority().compareTo(t2.getPriority());
        }
    }
}
