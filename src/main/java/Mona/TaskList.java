package Mona;
import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> tasks = new ArrayList<>();
    private Storage storage;

    public TaskList(Storage storage) {
        this.storage = storage;
        storage.readLog(this.tasks);
    }
    public void displayList() {
        System.out.println("  ____________________________________________________________");
        for (int i = 0; i < tasks.size(); i++) {
            Task currTask = tasks.get(i);
            System.out.println("    " + (i + 1) + ". " + currTask);
        }
        System.out.println("  ____________________________________________________________");
    }
    public void addTask(Task task) {
        this.tasks.add(task);
        storage.writeToFile(tasks);
    }
    public void addTaskTest(Task task) {
        this.tasks.add(task);
    }
    public Task unmarkTask(int taskIndex) {
        Task currTask = this.tasks.get(taskIndex);
        currTask.setCompletion(false);
        storage.writeToFile(tasks);
        return currTask;
    }
    public Task markTask(int taskIndex) {
        Task currTask = this.tasks.get(taskIndex);
        currTask.setCompletion(true);
        storage.writeToFile(tasks);
        return currTask;
    }
    public Task deleteTask(int taskIndex) {
        Task removedTask = this.tasks.remove(taskIndex);
        storage.writeToFile(tasks);
        return removedTask;
    }
    public Task deleteTaskTest(int taskIndex) {
        Task removedTask = this.tasks.remove(taskIndex);
        return removedTask;
    }
    public int getNumberOfTasks() {
        return this.tasks.size();
    }
    public void showRelevantTasks(String keyword) {
        System.out.println("  ____________________________________________________________\n" + "   Give Mona a second to find relevant tasks...");
        for (int i = 0; i < tasks.size(); i++) {
            Task currTask = tasks.get(i);
            if (!currTask.description.contains(keyword)) {
                continue;
            }
            System.out.println("    " + (i + 1) + ". " + currTask);
        }
        System.out.println("  ____________________________________________________________");
    }
}
