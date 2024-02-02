import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class TaskList {

    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public int numberOfTasks() {
        return this.tasks.size();
    }

    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    public void addTask(Task newTask) {
        this.tasks.add(newTask);
    }

    public void deleteTask(int index) {
        this.tasks.remove(index);
    }

    public boolean noTasks() {
        return this.tasks.isEmpty();
    }

    public ListIterator<Task> getTasks() {
        return this.tasks.listIterator();
    }

}
