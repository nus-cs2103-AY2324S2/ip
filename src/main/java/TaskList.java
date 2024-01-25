import java.util.ArrayList;

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
    public void removeTask(int taskNumber) {
        tasks.remove(taskNumber - 1);
    }

    // Method to list all tasks
    public void listTasks() {
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            System.out.println((i + 1) + ". " + task.getDescription());
        }
    }
}