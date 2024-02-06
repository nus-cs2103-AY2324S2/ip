package task;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> list;

    public TaskList() {
        this.list = new ArrayList<>();
    }

    public void add(Task task) {
        this.list.add(task);
    }

    public Task mark(int index) {
        Task task = this.list.get(index);
        task.markAsDone();
        return task;
    }

    public Task unMark(int index) {
        Task task = this.list.get(index);
        task.markNotDone();
        return task;
    }

    public Task delete(int index) {
            return this.list.remove(index);
    }

    public ArrayList<Task> getList() {
        return this.list;
    }

    public int size() {
        return this.list.size();
    }

    /**
     * Finds tasks that contain the keyword.
     * @param keyword The keyword to search for.
     * @return An ArrayList of tasks that contain the keyword.
     */
    public ArrayList<Task> findTasks(String keyword) {
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (Task task : this.list) {
            if (task.getDescription().contains(keyword)) {
                foundTasks.add(task);
            }
        }
        return foundTasks;
    }
}
