package comparators;

import java.util.Comparator;

import tasks.Task;

/**
 * Comparator for use in sorting the list of tasks. This sorts in ascending order.
 */
public class NameComparator implements Comparator<Task> {
    public int compare(Task t1, Task t2) {
        return t1.getName().compareTo(t2.getName());
    }
}
