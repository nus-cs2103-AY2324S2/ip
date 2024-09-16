package comparators;

import java.util.Comparator;

import tasks.Task;

/**
 * Comparator for use in sorting the list of tasks. This comparator sorts in descending order.
 */
public class NameComparatorDes implements Comparator<Task> {
    public int compare(Task t1, Task t2) {
        return t2.getName().compareTo(t1.getName());
    }
}
