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

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void deleteTask(int index) throws DukeException {
        if (index < 0 || index >= tasks.size()) {
            throw new DukeException("The task number is out of bounds.");
        }
        tasks.remove(index);
    }

    public Task getTask(int index) throws DukeException{
        if (index < 0 || index >= tasks.size()) {
            throw new DukeException("The task number is out of bounds.");
        }
        return tasks.get(index);
    }

    public int getSize() {
        return tasks.size();
    }

    public List<Task> getTasks() {
        return tasks;
    }
}
