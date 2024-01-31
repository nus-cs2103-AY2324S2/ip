package duke;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> lst;

    public TaskList() {
        this.lst = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> lst) {
        this.lst = lst;
    }

    public void addTask(Task newTask) {
        this.lst.add(newTask);
    }

    public void deleteTask(int num) {
        this.lst.remove(num);
    }

    public ArrayList<Task> getLst() {
        return this.lst;
    }

    public Task get(int num) {
        return this.lst.get(num);
    }

    public int size() {
        return this.lst.size();
    }
}