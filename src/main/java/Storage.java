import java.util.ArrayList;
import java.util.List;

public class Storage {

    // Data structure to store text entered by user
    private List<Task> items = new ArrayList<>();

    public void add(Task task) {
        items.add(task);
    }

    public List<Task> getItems() {
        return items;
    }

    public Task getItem(int index) {
        return items.get(index);
    }

    public void markDone(int index) {
        items.get(index).markDone();
    }

    public void unmarkDone(int index) {
        items.get(index).unmarkDone();
    }

    public void delete(int index) {
        items.remove(index);
    }
}
