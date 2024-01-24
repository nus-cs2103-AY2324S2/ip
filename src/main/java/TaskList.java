import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>(100);
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    public void addElements(String str) {
        Task task = new Task(str);
        tasks.add(task);
    }

    public void printElements() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i).toString());
        }
    }

    public void markIndexAsDone(int id) {
        Task task = tasks.get(id);
        task.setDone();
    }

    public void markIndexAsUndone(int id) {
        Task task = tasks.get(id);
        task.setNotDone();
    }

    public Task getTask(int id) {
        return tasks.get(id);
    }
}
