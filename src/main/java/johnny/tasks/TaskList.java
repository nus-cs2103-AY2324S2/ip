package johnny.tasks;

import java.util.ArrayList;
import java.util.List;

import johnny.exceptions.JohnnyException;

public class TaskList {

    private List<Task> tasks;

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public Task get(int index) throws JohnnyException {
        try {
            return tasks.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new JohnnyException("This task does not exist bro.");
        }
    }

    public Task mark(int index) throws JohnnyException {
        Task task = get(index);
        task.mark();
        return task;
    }

    public Task unmark(int index) throws JohnnyException {
        Task task = get(index);
        task.unmark();
        return task;
    }

    public Task delete(int index) throws JohnnyException {
        try {
            Task task = tasks.remove(index);
            return task;
        } catch (IndexOutOfBoundsException e) {
            throw new JohnnyException("This task does not exist bro.");
        }
    }

    public int size() {
        return tasks.size();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

}
