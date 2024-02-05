package eggy.task;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public int getSize() {
        return this.tasks.size();
    }

    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public Task removeTask(int index) {
        return this.tasks.remove(index);
    }

    public boolean isEmpty() {
        return this.tasks.isEmpty();
    }

    public List<Task> findMatchingTasks(String name) {
        return this.tasks.stream().filter(task -> task.getName().contains(name)).collect(Collectors.toList());
    }

    public String toFileString() {
        StringBuilder fileString = new StringBuilder();
        for (Task task : this.tasks) {
            fileString.append(task.toFileString()).append("\n");
        }
        return fileString.toString();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.getSize(); i++) {
            sb.append("     ").append(i + 1).append(".").append(this.getTask(i));
            if (i != this.getSize() - 1) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }
}
