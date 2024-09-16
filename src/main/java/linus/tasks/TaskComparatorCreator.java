package linus.tasks;

import java.time.LocalDateTime;
import java.util.Comparator;

public class TaskComparatorCreator {
    
    /**
     * Sort by alphabetical description order
     * @return Comparator<Task> sorts by alphabetical description
     */
    public static Comparator<Task> getDescriptionComparator() {
        return new Comparator<Task>() {

            @Override
            public int compare(Task task1, Task task2) {
                String description1 = task1.getDescription();
                String description2 = task2.getDescription();
                return description1.compareToIgnoreCase(description2);
            }

        };
    }

    /**
     * Sort by different task type
     * @return Comparator<Task> sorts by task type
     */
    public static Comparator<Task> getTaskComparator() {
        return new Comparator<Task>() {

            @Override
            public int compare(Task task1, Task task2) {
                String taskType1 = task1.getTaskType();
                String taskType2 = task2.getTaskType();
                return taskType1.compareTo(taskType2);
            }
        };
    }

    /**
     * Sort by marked and unmarked
     * @return Comparator<Task> sorts by mark status
     */
    public static Comparator<Task> getMarkComparator() {
        return new Comparator<Task>() {

            @Override
            public int compare(Task task1, Task task2) {
                String mark1 = task1.getStatusIcon();
                String mark2 = task2.getStatusIcon();
                return mark1.compareTo(mark2);
            }
        };
    }

    /**
     * Sort by task first then by date
     * @return Comparator<Task> sorts by date
     */
    public static Comparator<Task> getDateComparator() {
        return new Comparator<Task>() {

            @Override
            public int compare(Task task1, Task task2) {
                LocalDateTime taskTime1 = task1.getDate();
                LocalDateTime taskTime2 = task2.getDate();
                return taskTime1.compareTo(taskTime2);
            }
        };
    }
}