package earl.util;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import earl.tasks.Task;

/**
 * Class encapsulating the list of tasks.
 * <p>
 * Functionally a wrapper of the {@code List} class.
 */
public class TaskList {

    private final List<Task> tasks;

    /** Class constructor. */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /** Class constructor specifying existing list. */
    public TaskList(List<Task> tasks) {
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
    public List<Task> getList() {
        return tasks;
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public Task delete(int idx) {
        return tasks.remove(idx);
    }
}
