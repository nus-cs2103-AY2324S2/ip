package shodan;

import shodan.tasks.Task;

import java.util.List;

public class TaskList {
    private List<Task> tasks;

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public int add(Task task) {
        tasks.add(task);
        return tasks.size();
    }

    public Task delete(int index) throws IndexOutOfBoundsException {
        return tasks.remove(index);
    }

    public Task mark(int index, boolean done) throws IndexOutOfBoundsException {
        Task task = tasks.get(index);
        if (done) {
            task.done();
        } else {
            task.undone();
        }
        return task;
    }

    public List<Task> getTasks() {
        return tasks;
    }
}
