package duke;

import java.util.Comparator;

public class TaskPriorityComparator implements Comparator<Task> {

    @Override
    public int compare(Task t1, Task t2) {
        if (t1 instanceof ToDo && !(t2 instanceof ToDo)) {
            return -1;
        } else if (!(t1 instanceof ToDo) && t2 instanceof ToDo) {
            return 1;
        }
        if (t1.getDate() != null && t2.getDate() != null) {
            return t1.getDate().compareTo(t2.getDate());
        }

        // If one of them doesn't have a date, consider them equal
        return 0;

        //return compareByDate(t1, t2);
    }

    public int compareByDate(Task t1, Task t2) {
        if (t1.getDate() == null && t2.getDate() == null) {
            return 0;
        } else if (t1.getDate() == null) {
            return -1;
        } else if (t2.getDate() == null) {
            return 1;
        }
        return t1.getDate().compareTo(t2.getDate());
    }


}
