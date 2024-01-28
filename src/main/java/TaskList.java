import java.util.ArrayList;
import java.util.Iterator;

public class TaskList implements Iterable<Task> {
    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public boolean isEmpty() {
        return taskList.isEmpty();
    }

    public void add(Task taskToAdd) {
        this.taskList.add(taskToAdd);
    }

    public int size() {
        return this.taskList.size();
    }

    public Task get(int index) {
        return this.taskList.get(index);
    }

    public Task remove(int index) {
        return this.taskList.remove(index);
    }
    @Override
    public Iterator<Task> iterator() {
        return taskList.iterator();
    }
}
