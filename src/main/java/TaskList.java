import java.util.ArrayList;

public class TaskList {
    protected ArrayList<Task> list;

    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    public TaskList() {
        this.list = new ArrayList<>();
    }

    public void add(Task task) {
        list.add(task);
    }

    public void remove(int index) {
        list.remove(index);
    }

    public Task get(int index) {
        return list.get(index);
    }

    public int size() {
        return list.size();
    }

    public ArrayList<Task> getList() {
        return list;
    }

    @Override
    public String toString() {
        return list.toString();
    }
}
