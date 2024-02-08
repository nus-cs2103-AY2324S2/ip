package liv.container;

import java.util.ArrayList;
import liv.task.Task;

public class TaskList {
    private static ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public ArrayList<Task> getTaskList() {
        return tasks;
    }

    public static int getListSize() {
        return tasks.size();
    }

    public static Task getTask(int index) {
        return tasks.get(index);
    }
    public static void addTask(Task task) {
        tasks.add(task);
    }
    public Task deleteTask(int index) {
        return tasks.remove(index);
    }

    /**
     * Returns all tasks with the description that contains the keyword.
     * @param keyword The keyword that would be in the description returned.
     * @return A list of tasks that match the keyword.
     */
    public ArrayList<String> findMatchingTasks(String keyword) {
        ArrayList<String> matchingTasks = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.isInDescription(keyword)) {
                matchingTasks.add(task.getDisplayedString());
            }
        }
        return matchingTasks;
    }
}
