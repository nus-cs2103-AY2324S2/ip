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

    public Task removeTask(int index) throws DukeException {
        if (index < 0 || index >= tasks.size()) {
            throw new DukeException("The task index is out of range.");
        }
        return tasks.remove(index);
    }

    public Task getTask(int index) throws DukeException {
        if (index < 0 || index >= tasks.size()) {
            throw new DukeException("The task index is out of range.");
        }
        return tasks.get(index);
    }

    public ArrayList<Task> getTasks() {
        return new ArrayList<>(tasks);
    }

    public int getSize() {
        return tasks.size();
    }

}
