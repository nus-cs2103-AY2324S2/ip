package shodan;

import shodan.tasks.Task;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<Task> findTasks(List<String> keywords) {
        return tasks.stream()
                .filter(task -> keywords.stream().anyMatch(keyword -> task.getName().contains(keyword)))
                .collect(Collectors.toList());
    }

    public List<Task> getTasks() {
        return tasks;
    }
}
