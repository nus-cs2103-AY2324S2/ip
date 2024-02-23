package belle.run;

import java.util.ArrayList;

import belle.tasks.Task;

/**
 * Contains the task list e.g., it has
 * operations to add/delete tasks in the list.
 */
public class TaskList {
    private ArrayList<Task> list;

    /**
     * Constructs TaskList.
     *
     * @param list List that represents
     *          the programs tasklist.
     */
    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    public ArrayList<Task> getList() {
        return this.list;
    }

    public Task getTask(int index) {
        return list.get(index);
    }

    /**
     * Removes task at a specific index from
     * TaskList.
     *
     * @param  i Index of item to remove.
     */
    public void removeTask(int i) {
        list.remove(i);
    }

    public int getSize() {
        return list.size();
    }

    /**
     * Adds task to TaskList.
     *
     * @param t Task to add.
     */
    public void addTask(Task t) {
        list.add(t);
    }
}
