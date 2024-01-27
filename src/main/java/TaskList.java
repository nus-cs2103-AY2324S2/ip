import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void deleteTask(int index) throws ArtemisException {
        if (index < 0 || index >= tasks.size()) {
            throw new ArtemisException("Invalid task index. Please provide a valid index.");
        }

        tasks.remove(index);
    }

    public ArrayList<Task> getTasks() {
        return new ArrayList<>(tasks); // Return a copy to avoid direct modification
    }
}
