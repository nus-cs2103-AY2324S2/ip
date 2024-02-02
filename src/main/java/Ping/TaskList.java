package Ping;
import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public Task getTask(int idx) {
        return tasks.get(idx);
    }

    public int taskSize() {
        return tasks.size();
    }

    public Task deleteTask(int idx) {
        return tasks.remove(idx);
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public boolean emptyCheck() {
        return tasks.isEmpty();
    }

    public ArrayList<Task> allTasks() {
        return tasks;
    }


}
