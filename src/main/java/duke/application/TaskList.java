package duke.application;
import duke.task.Task;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public Task getTask(int i) {
        return tasks.get(i);
    }

    public ArrayList<Task> getTaskList() {
        return tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task removeTask(int index) {
        return tasks.remove(index);
    }

    public int getTotalTasks() {
        return tasks.size();
    }
}
