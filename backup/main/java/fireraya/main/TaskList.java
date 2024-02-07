package fireraya.main;

import fireraya.exception.FirerayaException;
import fireraya.task.Task;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> tasks;

    TaskList() {
        this.tasks = new ArrayList<>();
    }

    TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public int size() {
        return tasks.size();
    }

    public Task get(int i) {
        return tasks.get(i);
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public void markAsDone(int i) {
        tasks.get(i).markAsDone();
    }

    public void unmark(int i) {
        tasks.get(i).markAsUndone();
    }

    public void delete(int i) {
        tasks.remove(i);
    }

}
