package tasks;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void displayTasks() {
        for (int i = 0; i < tasks.size(); i++) {
            int j = i + 1;
            System.out.println(j + "." + tasks.get(i).toString());
        }
    }
    public void addTask(Task task) {
        tasks.add(task);
    }

    public void deleteTask(int index) {
        tasks.remove(index);
    }

    public Task get(int index) {
        return tasks.get(index);
    }

    public int size() {
        return this.tasks.size();
    }


}
