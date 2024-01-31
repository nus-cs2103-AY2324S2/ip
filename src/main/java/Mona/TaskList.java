package Mona;
import java.util.ArrayList;
import java.util.List;

/**
 * This class contains all logic surrounding Mona's task list
 */
public class TaskList {
    private List<Task> tasks = new ArrayList<>();
    private Storage storage;

    /**
     * Constructor for Task List
     * @param storage The storage instance that works hand-in-hand for most of the task list logic
     */
    public TaskList(Storage storage) {
        this.storage = storage;
        storage.readLog(this.tasks);
    }

    /**
     * Display the contents of the task list
     */
    public void displayList() {
        System.out.println("  ____________________________________________________________");
        for (int i = 0; i < tasks.size(); i++) {
            Task currTask = tasks.get(i);
            System.out.println("    " + (i + 1) + ". " + currTask);
        }
        System.out.println("  ____________________________________________________________");
    }

    /**
     * Adds a task to the task list
     * @param task the task to be added
     */
    public void addTask(Task task) {
        this.tasks.add(task);
        storage.writeToFile(tasks);
    }
    /**
     * Adds a task to the task list for unit testing
     * @param task the task to be added
     */
    public void addTaskTest(Task task) {
        this.tasks.add(task);
    }

    /**
     * Unmarks the task at the given index in the task list
     * @param taskIndex the index of the target task in the task list
     * @return the unmarked task
     */
    public Task unmarkTask(int taskIndex) {
        Task currTask = this.tasks.get(taskIndex);
        currTask.setCompletion(false);
        storage.writeToFile(tasks);
        return currTask;
    }

    /**
     * Marks the task at the given index in the task list
     * @param taskIndex the index of the target task in the task list
     * @return the marked task
     */
    public Task markTask(int taskIndex) {
        Task currTask = this.tasks.get(taskIndex);
        currTask.setCompletion(true);
        storage.writeToFile(tasks);
        return currTask;
    }

    /**
     * Deletes the task at the given index in the task list
     * @param taskIndex the index of the target task in the task list
     * @return the deleted task
     */
    public Task deleteTask(int taskIndex) {
        Task removedTask = this.tasks.remove(taskIndex);
        storage.writeToFile(tasks);
        return removedTask;
    }

    /**
     * Deletes the task at the given index in the task list for unit testing
     * @param taskIndex the index of the target task in the task list
     * @return the deleted task
     */
    public Task deleteTaskTest(int taskIndex) {
        Task removedTask = this.tasks.remove(taskIndex);
        return removedTask;
    }

    /**
     * Provides the number of tasks presently in the task list
     * @return the number of tasks in the list
     */
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
