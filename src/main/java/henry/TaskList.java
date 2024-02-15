package henry;

import java.util.ArrayList;

import henry.task.Task;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    private ArrayList<Task> items;

    public TaskList() {
        this.items = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> items) {
        this.items = items;
    }

    /**
     * Adds a task to the list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        items.add(task);
    }

    /**
     * Marks a task as done.
     *
     * @param index The index of the task to be marked as done.
     * @throws HenryException If the index is out of bounds.
     */
    public Task markTask(int index) throws HenryException {
        if (index < 0 || index >= items.size()) {
            throw new HenryException("The index is out of bounds!");
        }
        items.get(index).markAsDone();
        return items.get(index);
    }

    /**
     * Marks a task as undone.
     *
     * @param index The index of the task to be marked as undone.
     * @throws HenryException If the index is out of bounds.
     */
    public Task unmarkTask(int index) throws HenryException {
        if (index < 0 || index >= items.size()) {
            throw new HenryException("The index is out of bounds!");
        }
        items.get(index).unmarkAsDone();
        return items.get(index);
    }

    /**
     * Deletes a task from the list.
     *
     * @param index The index of the task to be deleted.
     * @throws HenryException If the index is out of bounds.
     */
    public String deleteTask(int index) throws HenryException {
        if (index < 0 || index >= items.size()) {
            throw new HenryException("The index is out of bounds!");
        }
        String ret = String.format("This task is deleted :)\n%s\n", items.get(index));
        items.remove(index);
        return ret;
    }

    /**
     * Finds tasks by keyword.
     *
     * @param keyword The keyword to be searched for.
     * @return The list of tasks that contain the keyword.
     */
    public ArrayList<Task> findTasksByKeyword(String keyword) {
        ArrayList<Task> ret = new ArrayList<>();
        for (Task task : items) {
            if (task.containsKeyword(keyword)) {
                ret.add(task);
            }
        }
        return ret;
    }

    /**
     * Returns the number of tasks in the list.
     * @return Number of tasks in the list.
     */
    public int getNumOfTasks() {
        return items.size();
    }

    /**
     * Gets the list of tasks.
     */
    public ArrayList<Task> getTasks() {
        return new ArrayList<>(items);
    }

    /**
     * Returns the list of tasks.
     *
     * @return The list of tasks.
     */
    public ArrayList<String> getFileStrings() {
        ArrayList<String> ret = new ArrayList<>();
        for (Task item : items) {
            ret.add(item.toFileString());
        }
        return ret;
    }
}
