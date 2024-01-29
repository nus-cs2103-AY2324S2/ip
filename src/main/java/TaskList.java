import java.util.ArrayList;
import java.util.List;

public class TaskList {

    private List<Task> tasks;

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public Task get(int index) {
        return tasks.get(index);
    }

    public Task mark(int index) throws JohnnyException {
        try {
            Task task = tasks.get(index);
            task.mark();
            return task;
        } catch (IndexOutOfBoundsException e) {
            throw new JohnnyException("This task does not exist bro.");
        }
    }

    public Task unmark(int index) throws JohnnyException {
        try {
            Task task = tasks.get(index);
            task.unmark();
            return task;
        } catch (IndexOutOfBoundsException e) {
            throw new JohnnyException("This task does not exist bro.");
        }
    }

    public Task delete(int index) throws JohnnyException {
        try {
            Task task = tasks.remove(index);
            return task;
        } catch (IndexOutOfBoundsException e) {
            throw new JohnnyException("This task does not exist bro.");
        }
    }

    public int size() {
        return tasks.size();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

}
