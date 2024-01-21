import java.util.ArrayList;
import java.util.stream.IntStream;

public class Duke {
    private ArrayList<Task> tasks;

    public Duke() {
        tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void getTask(int index) throws TaskNotFound {
        try {
            tasks.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new TaskNotFound("Could not find task.", e);
        }
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            s.append(i).append(". ").append(tasks.get(i)).append("\n");
        }
        s.deleteCharAt(s.length() - 1);
        return s.toString();
    }

    public static class TaskNotFound extends Exception {
        public TaskNotFound(String message, Throwable cause) {
            super(message, cause);
        }
    }
}
