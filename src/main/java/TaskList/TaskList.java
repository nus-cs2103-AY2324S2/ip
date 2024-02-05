package TaskList;

import TaskList.Tasks.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a list of tasks.
 * A <code>TaskList</code> object corresponds to a list of tasks
 * e.g., <code>cachedTasks</code>
 */
public class TaskList {
    private List<Task> cachedTasks;
    public TaskList() {
        this.cachedTasks = new ArrayList<>();
    }
    private List<Task> getTasks() {
        return this.cachedTasks;
    }
    public void clear() { this.cachedTasks.clear();}
    public int size() { return this.cachedTasks.size();}
    public void addTask(Task task) {
        this.cachedTasks.add(task);
    }

    public void markTask(int index) {
        this.cachedTasks.get(index).markCompleted();
    }
    public void unmarkTask(int index) {
        this.cachedTasks.get(index).markUncompleted();
    }
    public void delete(int index) {
        this.cachedTasks.remove(index);
    }
    public Task getTask(int index) {
        return this.cachedTasks.get(index);
    }
}
