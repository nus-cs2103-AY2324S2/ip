package chaterpillar.tasks;

import java.util.ArrayList;

import chaterpillar.datetime.DateTime;

/**
 * Custom wrapper class for list of tasks in this application.
 *
 * @author marclamp
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Basic constructor that creates a new empty
     * <code>TaskList</code> object.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Overloaded constructor that creates a new
     * <code>TaskList</code> object filled with
     * the list of tasks provided.
     *
     * @param tasks list of tasks
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Gets a task at the specified index.
     *
     * @param index specified index to be retrieved
     * @return <code>Task</code> object at specified index
     */
    public Task get(int index) {
        return this.tasks.get(index);
    }

    /**
     * Gets the entire list of tasks
     *
     * @return <code>ArrayList</code> of <code>Task</code>
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Gives the number of tasks in the list.
     *
     * @return number of tasks
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Adds a <code>Task</code> to the list.
     *
     * @param task <code>Task</code> to be added
     */
    public void addTask(Task task) {
        this.tasks.add(task);
        assert !this.tasks.isEmpty() : "ArrayList<Task> should not be empty after add function";
    }

    /**
     * Deletes a <code>Task</code> at the specified index.
     *
     * @param index specified index to be deleted
     * @return the <code>Task</code> that was deleted.
     */
    public Task deleteTaskAtIndex(int index) {
        return this.tasks.remove(index);
    }

    /**
     * Gets a list of tasks where its date corresponds to the
     * specified date.
     *
     * @param date specified date.
     * @return <code>TaskList</code> object containing a list of tasks
     */
    public TaskList getTasksOnDate(DateTime date) {
        ArrayList<Task> tasksToDisplayList = new ArrayList<Task>();
        for (Task task : tasks) {
            if (task.getHasDate()) {
                if (task.isWithinDate(date)) {
                    tasksToDisplayList.add(task);
                }
            }
        }
        return new TaskList(tasksToDisplayList);
    }
}
