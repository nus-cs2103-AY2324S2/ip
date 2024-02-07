package duke.task;

import java.util.ArrayList;
public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> list) {
        this.tasks = list;
    }

    public int size() {
        return this.tasks.size();
    }

    public Task get(int index) {
        return this.tasks.get(index);
    }

    public void remove(int index) {
        this.tasks.remove(index);
    }

    public void add(Task t) {
        this.tasks.add(t);
    }
}
