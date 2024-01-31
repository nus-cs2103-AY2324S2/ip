package capone;

import capone.tasks.Task;

import java.util.ArrayList;
import java.util.Iterator;

public class TaskList implements Iterable<Task>{
    private final ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public int getSize() {
        return this.taskList.size();
    }

    public Task getTask(int ndx) {
        return this.taskList.get(ndx);
    }

    public Task getLastTask() {
        return this.taskList.get(this.getSize()-1);
    }

    public Task removeTask(int ndx) {
        return this.taskList.remove(ndx);
    }

    public boolean addTask(Task task) {
        return this.taskList.add(task);
    }

    @Override
    public Iterator<Task> iterator() {
        return this.taskList.iterator();
    }

    public boolean isEmpty() {
        return this.taskList.isEmpty();
    }
}
