import java.util.ArrayList;

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

    public ArrayList<Task> getList() {
        return tasks;
    }

    public boolean mark(int idx) {
        return tasks.get(idx).markAsDone();
    }

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
