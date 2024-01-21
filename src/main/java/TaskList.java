import java.util.*;

public class TaskList {
    private final ArrayList<Task> tasks;
    public TaskList() {
        tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public Task getTask(int index) throws TaskNotFound {
        try {
            return tasks.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new TaskNotFound(index, e);
        }
    }

    public void deleteTask(int index) throws TaskNotFound {
        try {
            tasks.remove(index);
        } catch(IndexOutOfBoundsException e) {
            throw new TaskNotFound(index, e);
        }
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            s.append(i + 1).append(". ").append(tasks.get(i)).append("\n");
        }
        return s.toString();
    }

    public static class TaskNotFound extends Exception {
        public TaskNotFound(int index, Throwable cause) {
            super("Could not find task " + (index + 1), cause);
        }
    }
}
