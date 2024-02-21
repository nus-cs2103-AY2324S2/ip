package linus;

import java.util.ArrayList;

/**
 * Represents the ArrayList of Tasks.
 */
public class TaskList {
    private ArrayList<Task> taskList;

    /**
     * Constructs a TaskList.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Adds Task into TaskList.
     *
     * @param task The Task to be added.
     */
    public void addTask(Task task) {
        this.taskList.add(task);
    }

    /**
     * Removes Task at the input index within TaskList.
     *
     * @param index Index of the Task to be removed.
     */
    public void removeTask(int index) {
        this.taskList.remove(index);
    }

    /**
     * Returns the Task at the specified index within TaskList.
     *
     * @param index Index within TaskList of the Task to be retrieved.
     * @return Task to be retrieved.
     */
    public Task getTask(int index) {
        return this.taskList.get(index);
    }

    /**
     * Returns the number of Tasks in TaskList.
     *
     * @return Number of Tasks in TaskList.
     */
    public int getSize() {
        return this.taskList.size();
    }

    /**
     * Adds all Tasks in input TaskList into the current iterated TaskList.
     *
     * @param taskList TaskList containing Tasks to be added.
     */
    // rename needed below
    public void addAll(ArrayList<Task> taskList) {
        this.taskList.addAll(taskList);
    }

    /**
     * Returns TaskList containing all Tasks.
     *
     * @return TaskList containing all Tasks.
     */
    public ArrayList<Task> getAllTasks() {
        return this.taskList;
    }

    public ArrayList<Task> findMatchingTasks(String findKeyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();

        for (Task task : taskList) {
            if (task.getDescription().contains(findKeyword)) {
                matchingTasks.add(task);
            }
        }

        return matchingTasks;
    }
}
