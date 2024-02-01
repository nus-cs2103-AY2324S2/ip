import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {

        this.tasks.add(task);
    }

    public void deleteTask(int index) {
        this.tasks.remove(index);
    }

    public int size() {
        return tasks.size();
    }

    public String printTask(int index) {
        return tasks.get(index).toString();
    }

    public void mark(int index) {
        tasks.get(index).markDone();
    }

    public void unmark(int index) {
        tasks.get(index).markNotDone();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }
}
