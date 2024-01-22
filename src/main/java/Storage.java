// import ArrayList
import java.util.ArrayList;

public class Storage {
    private ArrayList<Task> tasks = new ArrayList<>();

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void listTasks() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

    public String getTaskDescription(int index) {
        return tasks.get(index).toString();
    }

    public void markTaskAsDone(int index) {
        tasks.get(index).markAsDone();
    }

    public void unmarkTaskAsDone(int index) {
        tasks.get(index).unmarkAsDone();
    }
};
