import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> items;
    public TaskList(ArrayList<Task> items) {
        this.items = items;
    }
    public Task markTask(int index) {
        return items.get(index).markDone();
    }
    public Task unMarkTask(int index) {
        return items.get(index).unMarkDone();
    }
    public Task delete(int index) {
        return items.remove(index);
    }
    public Task addTask(Task t) {
        items.add(t);
        return t;
    }
    public ArrayList<Task> list() {
        return this.items;
    }
}
