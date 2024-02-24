package tasklist;

import java.util.List;
import java.util.stream.Collectors;

import exceptions.CalException;
import tasks.Task;

public class TaskList {
    protected List<Task> tasks;

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void add(Task task) {
        tasks.add(task);
        return;
    }

    public Task mark(int taskNum) throws CalException {
        if (taskNum < 1 || taskNum > tasks.size()) {
            throw new CalException("Invalid task number!");
        }
        Task t = tasks.get(taskNum - 1);
        t.setStatus(true);
        return t;
    }

    public Task unmark(int taskNum) throws CalException {
        if (taskNum < 1 || taskNum > tasks.size()) {
            throw new CalException("Invalid task number!");
        }
        Task t = tasks.get(taskNum - 1);
        t.setStatus(false);  
        return t;
    }

    public Task delete(int taskNum) {
        Task t = tasks.get(taskNum - 1);
        tasks.remove(taskNum - 1);
        return t;
    }

    public List<Task> find(String keyword) {
        List<Task> tasksContainingKeyword = this.tasks.stream()
                .filter(task -> task.getDescription().contains(keyword))
                .collect(Collectors.toList());
        return tasksContainingKeyword;
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public int getSize() {
        return tasks.size();
    }
}
