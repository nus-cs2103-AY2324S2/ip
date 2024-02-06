package ChatbotRan;

import java.util.ArrayList;
import java.util.function.Consumer;

public class TaskList {
    private final ArrayList<Task> tasks;
    TaskIO taskStore;

    public TaskList(TaskIO taskStore) {
        this.taskStore = taskStore;
        this.tasks = taskStore.findTasks();
    }

    public Task get(int i) {
        return tasks.get(i);
    }

    public void remove(Task t) {
        tasks.remove(t);
        updateTasks();
    }

    public void changeTask(int index, Consumer<Task> func) {
        func.accept(tasks.get(index));
        updateTasks();
    }

    public void set(int index, Task task) {
        tasks.set(index, task);
        updateTasks();
    }

    public void updateTasks() {
        taskStore.writeTasks(tasks);
    }

    public void add(Task t) {
        tasks.add(t);
        updateTasks();
    }

    public int size() {
        return tasks.size();
    }

    public ArrayList<Task> find(String substring) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task t: tasks) {
            if (t.getContents().contains(substring)) {
                matchingTasks.add(t);
            }
        }
        return matchingTasks;
    }
}