import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task deleteTask(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= tasks.size()) {
            throw new IndexOutOfBoundsException("Task index is out of bounds.");
        }
        return tasks.remove(index);
    }

    public Task getTask(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= tasks.size()) {
            throw new IndexOutOfBoundsException("Task index is out of bounds.");
        }
        return tasks.get(index);
    }

    public int size() {
        return tasks.size();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void markTask(int index) throws IndexOutOfBoundsException {
        getTask(index).isDone = true;
    }

    public void unmarkTask(int index) throws IndexOutOfBoundsException {
        getTask(index).isDone = false;
    }

    // ... Other task list operations ...
}