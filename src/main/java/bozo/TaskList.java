package bozo;

import java.util.ArrayList;
import java.util.Iterator;

public class TaskList implements Iterable<Task> {

    public ArrayList<Task> list;

    public TaskList() {
        list = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        list = tasks;
    }

    public Task getTask(int i) {
        return list.get(i);
    }

    public int getSize() {
        return list.size();
    }

    public void addTask(Task task) {
        list.add(task);
    }

    public Task removeTask(int index) {
        return list.remove(index);
    }

    @Override
    public Iterator<Task> iterator() {
        return list.iterator();
    }

}
