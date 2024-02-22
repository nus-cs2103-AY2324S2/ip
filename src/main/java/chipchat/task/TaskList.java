package chipchat.task;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a list that manages tasks.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Constructor of task if no initial tasks are given.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructor of task if initial tasks are given.
     */
    public TaskList(ArrayList<Task> initTasks) {
        this.tasks = initTasks;
    }

    /**
     * Adds a new task to this list.
     *
     * @param task task to be added to the list
     */
    public void add(Task task) {
        this.tasks.add(task);
    }

    /**
     * Removes the task specified by the index from this list.
     *
     * @param index index of task in the list to be removed
     * @return task removed from the list
     */
    public Task delete(int index) {
        return this.tasks.remove(index);
    }

    /**
     * Marks the task specified by the index as done.
     *
     * @param index indec of task in the list to be marked as done
     */
    public void mark(int index) {
        this.tasks.get(index).mark();
    }

    /**
     * Unmarks the task specified by the index as not done.
     *
     * @param index index of task in the list to be unmarked as not done
     */
    public void unmark(int index) {
        this.tasks.get(index).unmark();
    }

    /**
     * Returns a list of tasks that contains the queried string.
     *
     * @param query the string to be queried
     * @return list of tasks
     */
    public List<Task> queryByString(String query) {
        ArrayList<Task> candidates = new ArrayList<>();
        for (Task task : this.tasks) {
            if (task.matchByString(query)) {
                candidates.add(task);
            }
        }
        return candidates;
    }

    /**
     * Returns the string representation of the task in the list specified by the index
     *
     * @param index index of task in the list to be printed
     * @return string representation of task specified by the index
     */
    public String printTask(int index) {
        return this.tasks.get(index).toString();
    }

    /**
     * Returns the string representation of all tasks in the list.
     *
     * @return string representation of all tasks
     */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < this.tasks.size(); i++) {
            str.append(String.format("%d. %s\n", i, tasks.get(i)));
        }
        return str.toString();
    }

    /**
     * Returns the data-format string of all tasks in the list. For Chipchat specific storage purposes.
     *
     * @return a string representation of all tasks in the list in a parsable format
     */
    public String dataString() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < this.tasks.size(); i++) {
            str.append(String.format("%d. %s\n", i, tasks.get(i).dataString()));
        }
        return str.toString();
    }
}
