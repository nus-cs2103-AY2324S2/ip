package slaybot;

import entity.Task;

import java.time.LocalDateTime;
import java.util.Comparator;

public class TaskComparator implements Comparator<Task> {
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
