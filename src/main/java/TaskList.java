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

    public Task deleteTask(int index) throws DukeException {
        if (index < 0 || index >= tasks.size()) {
            throw new DukeException(" OOPS! Your Only Friend could not find that index.");
        }
        Task deletedTask = tasks.get(index);
        tasks.remove(index);

        return deletedTask;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public int getSize() {
        return tasks.size();
    }

}
