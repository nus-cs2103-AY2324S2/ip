package seedu.mamta;

import java.util.ArrayList;
import java.util.stream.Collectors;


/**
 * Manages the list of tasks.
 */
public class TaskList {

    /**
     * List to store the history of tasks.
     */
    private static ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Adds a task to the list.
     * @param t The task to be added.
     */
    public static void addTask(Task t) {
        tasks.add(t);
    }

    /**
     * Removes a task from the list.
     * @param t The task to be removed.
     */
    public static void removeTask(Task t) {
        tasks.remove(t);
    }

    /**
     * Retrieves the history of tasks.
     * @return The list of tasks.
     */
    public static ArrayList<Task> getTasks() {
        return TaskList.sortTaskListByDates();
    }

    /**
     * Clears the list of tasks.
     */
    public static void clear() {
        TaskList.tasks = new ArrayList<>();
    }

    public static String searchOutput(String keyword) {
        return tasks.stream()
                .filter(task -> task.getContent().contains(keyword))
                .map(task -> String.format("%d. %s\n", tasks.indexOf(task) + 1, task))
                .collect(Collectors.joining());
    }

    public static ArrayList<Task> sortTaskListByDates() {
        ArrayList<Task> sortedTasks = new ArrayList<Task>();
        //add tasks without explicit dates at the top before sorting
        for (Task t: tasks) {
            if (t instanceof Todo) {
                sortedTasks.add(t);
                tasks.remove(t);
            }
        }
        //O(n^2)
        for (int i = 0; i < tasks.size()-1; i++) {
            for (int j = i+1; j < tasks.size(); j++) {
                if (tasks.get(i).getStartDate() > tasks.get(j).getStartDate()) {
                    Task temp = tasks.get(i);
                    tasks.set(i, tasks.get(j));
                    tasks.set(j, temp);
                }
            }
        }
        //append the sorted list to sortedTasks
        sortedTasks.addAll(tasks);
        tasks = sortedTasks;
        return tasks;
    }

}
