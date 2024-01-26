package roland;

import roland.task.Task;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public ArrayList<Task> getList() {
        return this.tasks;
    }

    public void remove(int index) {
        this.tasks.remove(index);
    }

    public Task get(int index) {
        return this.tasks.get(index);
    }

    public void add(Task task) {
        this.tasks.add(task);
    }

    public int size() {
        return this.tasks.size();
    }



}
