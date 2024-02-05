import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> list;

    TaskList() {
        this.list = new ArrayList<>();
    }

    public void add(Task task) {
        this.list.add(task);
    }

    public Task mark(int index) {
        Task task = this.list.get(index);
        task.markAsDone();
        return task;
    }

    public Task unMark(int index) {
        Task task = this.list.get(index);
        task.markNotDone();
        return task;
    }

    public Task delete(int index) {
            return this.list.remove(index);
    }

    public ArrayList<Task> getList() {
        return this.list;
    }

    public int size() {
        return this.list.size();
    }
}
