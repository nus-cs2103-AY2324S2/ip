import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> items;
    public TaskList(ArrayList<Task> items) {
        this.items = items;
    }
    public void markTask(int index) {
        items.get(index).markDone();
    }
    public void unMarkTask(int index) {
        items.get(index).unMarkDone();
    }
    public Task delete(int index) {
        return items.remove(index);
    }
    public void addTask(Task t) {
        items.add(t);
    }
    public ArrayList<Task> list() {
        return this.items;
    }
}
