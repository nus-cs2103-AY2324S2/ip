package liv.container;

import java.util.ArrayList;
import liv.task.Task;

public class TaskList {
    private static ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public ArrayList<Task> getTaskList() {
        return tasks;
    }

    public static int getListSize() {
        return tasks.size();
    }

    public static Task getTask(int index) {
        return tasks.get(index);
    }
    public static void addTask(Task task) {
        tasks.add(task);
    }
    public Task deleteTask(int index) {
        return tasks.remove(index);
    }
}
