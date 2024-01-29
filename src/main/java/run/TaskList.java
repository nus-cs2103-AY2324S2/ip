package run;

import java.util.ArrayList;

import tasks.Task;

public class TaskList {
    private ArrayList<Task> list = new ArrayList<>();

    public TaskList(ArrayList<Task> l) {
        this.list = l;
    }

    public ArrayList<Task> getList() {
        return this.list;
    }

    public Task getTask(int i) {
        return list.get(i);
    }

    public void removeTask(int i) {
        list.remove(i);
    }

    public int getSize() {
        return list.size();
    }

    public void addTask(Task t) {
        list.add(t);
    }
}
