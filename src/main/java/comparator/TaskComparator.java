package comparator;

import java.util.Comparator;

import task.Task;

/**
 * Comparator for comparing tasks based on their names.
 * 
 * @param <T> the type of tasks to compare, must extend the Task class
 */
public class TaskComparator<T extends Task> implements Comparator<T> {

    /**
     * Compares two tasks based on their names.
     * 
     * @param t1 the first task to compare
     * @param t2 the second task to compare
     * @return a negative integer, zero, or a positive integer as the first task is
     *         less than, equal to, or greater
     *         than the second.
     */
    @Override
    public int compare(T t1, T t2) {

        return t1.getName().compareTo(t2.getName());
    }
}
