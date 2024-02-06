package earl.util;

import java.util.ArrayList;

import earl.tasks.Task;

/**
 * Class encapsulating the list of tasks.
 * <p>
 * Functionally a wrapper of the {@code ArrayList} class.
 */
public class TaskList {

    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    public int getSize() {
        return tasks.size();
    }

    public Task get(int i) {
        return tasks.get(i);
    }

    /** Return list as an {@code ArrayList}. */
    public ArrayList<Task> getList() {
        return tasks;
    }

    /** Marks the task at the given index as done. Returns success. */
    public boolean mark(int idx) {
        return tasks.get(idx).markAsDone();
    }

    /** Marks the task at the given index as undone. Returns success. */
    public boolean unmark(int idx) {
        return tasks.get(idx).markUndone();
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public Task delete(int idx) {
        return tasks.remove(idx);
    }
}
