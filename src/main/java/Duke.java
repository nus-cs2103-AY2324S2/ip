import java.util.*;

public class Duke {
    private final ArrayList<Task> tasks;
    public Duke() {
        tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public Task getTask(int index) throws TaskNotFound {
        try {
            return tasks.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new TaskNotFound("Could not find task " + (index + 1), e);
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
        public TaskNotFound(String message, Throwable cause) {
            super(message, cause);
        }
    }
}
