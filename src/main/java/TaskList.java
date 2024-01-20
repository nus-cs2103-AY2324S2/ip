import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private final List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Returns the task at the specified 1-based index.
     *
     * @param index 1-based index of the task to return.
     * @return the task at the specified 1-based index.
     */
    public Task getTask(int index) {
        return this.tasks.get(index - 1);
    }

    public boolean validIndex(int index) {
        return index >= 1 && index <= this.tasks.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.tasks.size(); i++) {
            sb.append(i + 1)
                .append(". ")
                .append(this.tasks.get(i).toString())
                .append("\n");
        }
        return sb.toString();
    }
}
