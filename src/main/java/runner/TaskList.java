package runner;
import task.Task;
import java.util.ArrayList;
import java.util.List;

/**
 * Class for taskList to store tasks.
 */
public class TaskList {
    protected List<Task> task_list;

    /**
     * Constructor for a TaskList.
     */
    public TaskList() {
        this.task_list = new ArrayList<>();
    }

    /**
     * Return a task specified by the index.
     * @param n Index of the task.
     */
    public Task get_task(int n) {
        return task_list.get(n);
    }

    /**
     * Return the List<Task> inside.
     */
    public List<Task> get_list() {
        return this.task_list;
    }

    /**
     * Return the size of a TaskList.
     */
    public int size() {
        return task_list.size();
    }

    /**
     * Add one Task to TaskList.
     * @param tk Task added.
     */
    public void add(Task tk) {
        task_list.add(tk);
    }

    /**
     * Remove the task from the TaskList.
     * @param n Index of the task.
     */
    public void remove(int n) {
        task_list.remove(n);
    }
}
