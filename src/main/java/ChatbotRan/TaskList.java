package ChatbotRan;

import java.util.ArrayList;
import java.util.function.Consumer;

/**
 * Stores and automatically saves/reads Tasks, using the given TaskIO.
 */
public class TaskList {
    private final ArrayList<Task> tasks;
    TaskIo taskStore;

    public TaskList(TaskIo taskStore) {
        this.taskStore = taskStore;
        this.tasks = taskStore.findTasks();
    }

    /**
     * Retrieves task at given index.
     *
     * @param i index
     * @return Task at that index
     */
    public Task get(int i) {
        return tasks.get(i);
    }

    /**
     * Removes given task from the list and saves.
     *
     * @param t Task to remove
     */
    public void remove(Task t) {
        tasks.remove(t);
        updateTasks();
    }

    /**
     * Replaces the task at given index with the new task and saves.
     *
     * @param index index to replace
     * @param task  new task
     */
    public void set(int index, Task task) {
        assert 0 <= index && index < tasks.size();
        tasks.set(index, task);
        updateTasks();
    }

    /**
     * Saves the task list using the TaskIO.
     */
    public void updateTasks() {
        taskStore.writeTasks(tasks);
    }

    /**
     * Adds the given task and saves.
     *
     * @param t task
     */
    public void add(Task t) {
        tasks.add(t);
        updateTasks();
    }

    /**
     * Returns current number of tasks.
     */
    public int size() {
        return tasks.size();
    }

    public ArrayList<Task> find(String substring) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task t : tasks) {
            if (t.getContents().contains(substring)) {
                matchingTasks.add(t);
            }
        }
        return matchingTasks;
    }
}