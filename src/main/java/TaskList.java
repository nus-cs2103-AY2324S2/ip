import java.lang.reflect.Array;
import java.util.ArrayList;

public class TaskList {
    private Storage storage;
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public int size() {
        return tasks.size();
    }
    public void add(Task task) {
        tasks.add(task);
    }

    public Task remove(int index) {
        return tasks.remove(index);
    }

    public Task get(int index) {
        return tasks.get(index);
    }

    public ArrayList<Task> getTasks() {
        return new ArrayList<>(tasks);
    }
}
