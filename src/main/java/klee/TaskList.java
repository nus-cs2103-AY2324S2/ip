package klee;

import java.util.ArrayList;

import klee.task.Task;

/**
 * Contains function for interacting with the list of tasks.
 */
public class TaskList {
    protected ArrayList<Task> tasks;

    /**
     * Constructor for TaskList class.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Add task into tasks.
     *
     * @param task
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Remove task in index i from tasks.
     *
     * @param i
     */
    public void remove(int i) {
        tasks.remove(i);
    }

    /**
     * Return the task in index i of tasks.
     *
     * @param i
     * @return instance of Task.
     */
    public Task get(int i) {
        return tasks.get(i);
    }

    /**
     * Return number of tasks.
     *
     * @return number of tasks in tasks.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Checks if every task in obj is the same as this instance.
     *
     * @param obj
     * @return If obj is equal to this.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() == TaskList.class) {
            try {
                if (this.size() == ((TaskList) obj).size()) {
                    for (int i = 0; i < this.size(); i++) {
                        if (this.get(i) == ((TaskList) obj).get(i)) {
                            return false;
                        }
                    }
                    return true;
                } else {
                    return false;
                }
            } catch (Exception e) {
                return false;
            }
        } else {
            return false;
        }
    }
}
