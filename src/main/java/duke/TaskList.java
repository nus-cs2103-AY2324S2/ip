package duke;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> ls;
    public TaskList(ArrayList<Task> ls) {
        this.ls = ls;
    }

    public ArrayList<Task> getLs() {
        return ls;
    }

    public Task get(int i) {
        Task t = getLs().get(i);
        return t;
    }

    public int size() {
        return getLs().size();
    }

    public void add(Task t) {
        getLs().add(t);
    }

    public void remove(int taskNumtoRemove) {
        getLs().remove(taskNumtoRemove);
    }
}
