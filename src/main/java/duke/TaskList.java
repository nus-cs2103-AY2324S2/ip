package duke;
import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> tasks;

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void deleteTask(int number) {
        tasks.remove(number - 1);
    }

    public int getSize() {
        return tasks.size();
    }

    public Task getTask(int number) {
        return tasks.get(number - 1);
    }

    public void unmark(int number) {
        Task t = tasks.get(number - 1);
        t.markAsUnDone();
    }

    public void mark(int number) {
        Task t = tasks.get(number - 1);
        t.markAsDone();
    } 

}
