package duke;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Comparator;
public class TaskComparator implements Comparator<Task> {
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

    private LocalDateTime getDateTime(Task task) {
        if (task instanceof Event) {
            return LocalDateTime.of(((Event) task).getFrom(), LocalTime.MIN);
        } else {
            return ((Deadline) task).getBy();
        }
    }
}
