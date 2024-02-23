package asher.tasks;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    private final ArrayList<Task> tasks = new ArrayList<>();
    public static final int MAX_TASKS = 100;

    /**
     * Retrieves the total number of tasks in the list.
     *
     * @return The total number of tasks.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Retrieves the task at the specified index.
     *
     * @param index The index of the task to retrieve.
     * @return The task at the specified index if valid, else return null.
     */
    public Task get(int index) {
        if (index >= 0 && index < tasks.size()) {
            return tasks.get(index);
        } else {
            return null;
        }
    }

    /**
     * Retrieves the list of tasks.
     *
     * @return The list of tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Retrieves the index of a task based on its ID.
     *
     * @param taskId The ID of the task.
     * @return The index that the task is at.
     */
    public int getTaskIndexById(int taskId) {
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getTaskId() == taskId) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Retrieves the index of a task based on its ID extracted from the command.
     *
     * @param task The command containing the taskId.
     * @return The index that the task is at.
     */
    public int getTaskNumber(String task) {
        String[] word = task.split(" ");
        if (word.length == 2) {
            int taskId = Integer.parseInt(word[1]);
            for (int i = 0; i < tasks.size(); i++) {
                if (tasks.get(i).getId() == taskId) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * Updates the IDs of the tasks in the list.
     */
    public void updateTaskIds() {
        for (int i = 0; i < tasks.size(); i++) {
            tasks.get(i).setId(i + 1);
        }
    }

    /**
     * Add a task to the task list.
     *
     * @param task The task that should be added into the list.
     */
    public void addTask(Task task) {
        if (tasks.size() < MAX_TASKS) {
            tasks.add(task);
        }
    }

    /**
     * Deletes the task with the specified task ID from the task list.
     *
     * @param taskId The ID of the task to be deleted.
     * @return The task that was removed, else return null if invalid task.
     */
    public Task deleteTask(int taskId) {
        if (taskId < 0 || taskId >= tasks.size()) {
            return null;
        }
        return tasks.remove(taskId);
    }

    /**
     * Searches the keyword inside the list of task.
     *
     * @param keyword The word to search for.
     * @return All the tasks that contains the keyword.
     */
    public ArrayList<Task> searchKeyword(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();

        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }
}
