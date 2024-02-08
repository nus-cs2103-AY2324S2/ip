package luke.task;

import java.util.ArrayList;

/**
 * Represents a list of tasks, where each task is stored in an ArrayList.
 */
public class TaskList {
    private ArrayList<Task> list;

    /**
     * Constructs a TaskList object with an empty ArrayList.
     */
    public TaskList() {
        list = new ArrayList<>();

    }

    /**
     * Constructs a TaskList object with an ArrayList of tasks provided.
     *
     * @param list ArrayList of tasks.
     */
    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    /**
     * Adds a task to the list of tasks.
     *
     * @param task The task to be added to the list of tasks.
     */
    public void add(Task task) {
        list.add(task);
    }

    /**
     * Removes a task from the list of tasks.
     *
     * @param index The index of the task to be removed from the list of tasks.
     */
    public void remove(int index) {
        list.remove(index);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The number of tasks in the list.
     */
    public int size() {
        return list.size();
    }

    /**
     * Returns the task at the specified index in the list.
     *
     * @param index The index of the task in the list.
     * @return The task at the specified index in the list.
     */
    public Task get(int index) {
        return list.get(index);
    }

    /**
     * Returns the whole list of tasks.
     *
     * @return The whole list of tasks.
     */
    public ArrayList<Task> getList() {
        return list;
    }

    /**
     * Marks a task as done in the list of tasks using the index of the task.
     *
     * @param index The index of the task to be marked as done.
     */
    public void markTask(int index) {
        list.get(index).markAsDone();
    }

    /**
     * Marks a task as undone in the list of tasks using the index of the task.
     *
     * @param index The index of the task to be marked as undone.
     */
    public void unmarkTask(int index) {
        list.get(index).markAsUndone();
    }

    /**
     * Finds tasks in the list of tasks that contain the keyword.
     *
     * @param keyword The keyword to be found in the list of tasks.
     * @return The list of tasks that contains the keyword.
     */
    public TaskList find(String keyword) {
        TaskList foundList = new TaskList();
        for (Task task : list) {
            if (task.matchKeyword(keyword)) {
                foundList.add(task);
            }
        }
        return foundList;
    }

}
