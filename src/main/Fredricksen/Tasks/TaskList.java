package Tasks;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> list;

    public TaskList() {
        this.list = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    public void addTask(Task task) {
        this.list.add(task);
    }

    public void deleteTask(int task) {
        this.list.remove(task);
    }

    public void deleteTask(Task task) {
        this.list.remove(task);
    }

    public Task getTask(int index) {
        return this.list.get(index);
    }

    public ArrayList<Task> getList() {
        return this.list;
    }

    public int size() {
        return this.list.size();
    }
}
