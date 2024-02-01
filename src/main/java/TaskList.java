import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;
    private Integer size;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
        this.size = tasks.size();
    }

    public void addTask(Task task) {
        tasks.add(task);
        this.size += 1;
    }

    public Task getTask(int index) throws DukeException {
        if (index < 0 || index >= tasks.size()) {
            throw new DukeException("The task number you are trying to access does not exist.");
        }
        return tasks.get(index);
    }

    public Task deleteTask(int index) throws DukeException {
        if (index < 0 || index >= tasks.size()) {
            throw new DukeException("The task number you are trying to delete does not exist.");
        }
        size -= 1;
        return tasks.remove(index);
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public int getSize() {
        return this.size;
    }
}
