import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;

    TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void add(Task task) {
        this.tasks.add(task);
    }

    public Task get(int index) {
        return this.tasks.get(index);
    }

    public ArrayList<Task> getAllTasks() {
        return this.tasks;
    }

    public Task remove(int index) {
        return this.tasks.remove(index);
    }

    public int size() {
        return this.tasks.size();
    }

    public boolean isEmpty() {
        return this.tasks.isEmpty();
    }

}
