package duke.task;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    private List<Task> list;

    public TaskList() {
        this.list = new ArrayList<>();
    }

    public boolean addTask(Task task) {
        return list.add(task);
    }

    public Task deleteTask(int index) {
        return list.remove(index - 1);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(i + 1 + ". " + list.get(i) + "\n");
        }
        return sb.toString();
    }

    public void markTaskAsDone(int index) {
        list.get(index - 1).markAsDone();
    }

    public void unmarkTaskAsDone(int index) {
        list.get(index - 1).unmarkAsDone();
    }

    public Task get(int index) {
        return list.get(index);
    }

    public int size() {
        return list.size();
    }
}
