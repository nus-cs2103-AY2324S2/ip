import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;
    private int taskCounter;

    public TaskList() {
        this.tasks = new ArrayList<>();
        this.taskCounter = 0;
    }

    public void addTask(Task task) {
        tasks.add(task);
        taskCounter++;
    }

    public Task deleteTask(int index) throws DukeException {
        validateIndex(index);
        Task removedTask = tasks.remove(index);
        return removedTask;
    }

    public Task getTask(int index) throws DukeException {
        validateIndex(index);
        return tasks.get(index);
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    private void validateIndex(int index) throws DukeException {
        if (index < 0 || index >= tasks.size()) {
            throw new DukeException("Task not found. Please provide a valid task number.");
        }
    }

    public int size() {
        return tasks.size();
    }
}
