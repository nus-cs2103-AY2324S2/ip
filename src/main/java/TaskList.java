import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(String task) {
        tasks.add(new Task(task));
        System.out.println("added: " + task.toString() + "\n");
    }

    public void displayTasks() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(i + 1 + ". " + tasks.get(i).toString());
        }
        System.out.println();
    }

    public void markTaskAsDone(int index) {
        tasks.get(index - 1).markAsDone();
    }

    public void unmarkTaskAsDone(int index) {
        tasks.get(index - 1).unmarkDone();
    }
}