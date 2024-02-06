import java.util.ArrayList;
public class TaskList {
    private ArrayList<Task> tasks;

    TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public void changeTaskStatus(int index, boolean newStatus) {
        this.tasks.get(index).setStatus(newStatus);
    }

    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    public void addTask(Task task, int index) {
        this.tasks.add(index, task);
    }

    public int getSize() {
        return this.tasks.size();
    }

    public void removeTask(int index) {
        this.tasks.remove(index);
    }
}
