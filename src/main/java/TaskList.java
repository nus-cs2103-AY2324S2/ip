import java.util.ArrayList;
import java.util.List;

public class TaskList {
    // List of tasks to be done by the user
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    // Method to add task
    public void addTask(Task task) {
        tasks.add(task);
    }

    // Method to remove task
    // TODO: Implement this later
    public void removeTask(int taskNumber) {
        tasks.remove(taskNumber - 1);
    }

    // Method to list all tasks
    public List<String> listTasks() {
        List<String> taskDescriptions = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            taskDescriptions.add((i + 1) + ". " + task.toString());
        }
        return taskDescriptions;
    }

    // Method to mark task as done
    public void markTaskAsDone(int taskNumber) {
        tasks.get(taskNumber - 1).markAsDone();
    }

    // Method to mark task as undone
    public void markTaskAsUndone(int taskNumber) {
        tasks.get(taskNumber - 1).markAsUndone();
    }
}