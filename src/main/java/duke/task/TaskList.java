package duke.task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private final List<Task> tasks;

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public Task getTask(int id) {
        return this.tasks.get(id);
    }

    public void markAsDone(int id) {
        this.tasks.get(id).todoState = TodoState.DONE;
    }

    public void unmarkAsDone(int id) {
        this.tasks.get(id).todoState = TodoState.UNDONE;
    }

    public void add(Task task) {
        this.tasks.add(task);
    }

    public int size() {
        return this.tasks.size();
    }

    public void remove(int id) {
        this.tasks.remove(id);
    }
}

