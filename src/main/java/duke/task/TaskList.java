package duke.task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public int getSize() {
        return tasks.size();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task getTask(int index) {
        if (isValidIndex(index)) {
            return tasks.get(index - 1);
        }
        return null;
    }

    public Task markTaskAsDone(int index) {
        if (isValidIndex(index)) {
            Task task = tasks.get(index - 1);
            task.markAsDone();
            return task;
        }
        return null;
    }

    public Task unmarkTask(int index) {
        if (isValidIndex(index)) {
            Task task = tasks.get(index - 1);
            task.unmarkAsDone();
            return task;
        }
        return null;
    }

    public Task deleteTask(int index) {
        if (isValidIndex(index)) {
            return tasks.remove(index - 1);
        }
        return null;
    }

    private boolean isValidIndex(int index) {
        return index >= 1 && index <= tasks.size();
    }
}
