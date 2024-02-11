import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    // Overloaded constructor to initialize with an existing list of tasks
    public TaskList(List<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    // Add a task to the list
    public void addTask(Task task) {
        tasks.add(task);
    }

    // Get a task from the list by index
    public Task getTask(int index) throws IndexOutOfBoundsException {
        return tasks.get(index);
    }

    // Delete a task from the list by index
    public Task deleteTask(int index) throws IndexOutOfBoundsException {
        return tasks.remove(index);
    }

    // Get the entire list of tasks
    public List<Task> getTasks() {
        return tasks;
    }

    // Get the number of tasks in the list
    public int size() {
        return tasks.size();
    }
}


