package task;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * * Represents a collection of tasks in the Uncle Bob application.
 */
public class TaskList implements Iterable<Task> {

    private List<Task> tasks = new ArrayList<>();

    public TaskList() {}

    public void addTasks(Task task) {
        tasks.add(task);
    }

    public int numTasks() {
        return tasks.size();
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    public Task get(int i) {
        return tasks.get(i);
    }

    public Task remove(int i) {
        return tasks.remove(i);
    }

    /**
     * Retrieves tasks that are within a day of the current date.
     *
     * @return A string containing details of upcoming tasks.
     */

    public String upcomingTasks() {
        LocalDate currentDate = LocalDate.now();
        StringBuilder result = new StringBuilder();
        int appendedTasksCount = 0;

        // Append the current date to the result
        result.append("Current Date: ").append(currentDate).append("\n\n");

        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            LocalDate taskDate = null;
            if (task.getBy() != null) {
                taskDate = task.getBy();
            } else if (task.getStart() != null) {
                taskDate = task.getStart();
            }
            if (taskDate != null) {
                // Calculate the difference in days between the task date and the current date
                long daysUntilTask = ChronoUnit.DAYS.between(currentDate, taskDate);
                if (daysUntilTask == 0) {
                    result.append(appendedTasksCount + 1).append(". ").append(task).append("\n");
                    appendedTasksCount++;
                }
            }
        }
        result.append("\nYou have ").append(appendedTasksCount).append(" upcoming tasks!");
        return result.toString();
    }

    @Override
    public Iterator<Task> iterator() {
        return tasks.iterator();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < tasks.size(); i++) {
            result.append(i + 1).append(". ").append(tasks.get(i)).append("\n");
        }

        return result.toString();
    }
}
