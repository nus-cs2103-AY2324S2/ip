package tasks;

import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> list = new ArrayList<>();

    public TaskList() {
    }

    public void addTask(Task task) {
        list.add(task);
    }

    public void deleteTask(int index) {
        list.remove(index - 1);
    }

    public int getSize() {
        return list.size();
    }

    public Task getTask(int index) {
        return list.get(index);
    }
}
