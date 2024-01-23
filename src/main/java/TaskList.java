import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    // Add a task to the task list
    public void addTask(Task task) {
        tasks.add(task);
    }

    // Print the status of each task in the task list
    public void printTaskStatus() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            System.out.println((i + 1) + "." + task);
        }
    }

    public static void main(String[] args) {
    }
}