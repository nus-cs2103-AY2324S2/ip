package duke.tasks;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public Task get(Integer index) {
        return this.tasks.get(index);
    }

    public boolean add(Task task) {
        return this.tasks.add(task);
    }

    public Task remove(int i) {
        return this.tasks.remove(i);
    }

    public int size() {
        return this.tasks.size();
    }
}
