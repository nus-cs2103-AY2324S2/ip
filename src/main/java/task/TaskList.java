package task;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TaskList implements Iterable<Task> {

    private List<Task> tasks = new ArrayList<>();

    public TaskList() {}

    public void addTasks(Task task) {
        tasks.add(task);
    }

    public int numTasks() {
        return tasks.size();
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    public Task get(int i) {
        return tasks.get(i);
    }

    public Task remove(int i) {
        return tasks.remove(i);
    }

    @Override
    public Iterator<Task> iterator() {
        return tasks.iterator();
    }
}
