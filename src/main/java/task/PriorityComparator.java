package task;

import java.util.Comparator;

public class PriorityComparator implements Comparator<Task> {
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
