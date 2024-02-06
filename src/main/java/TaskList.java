import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> tasks;
    public TaskList() {
        this.tasks = new ArrayList<>();
    }
    public TaskList(List<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }
    public void addTask(Task task) {
        tasks.add(task);
        System.out.println("Added: " + task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }
    public void deleteTask(int taskNumber) throws DukeException {
        if (taskNumber < 1 || taskNumber > tasks.size()) {
            throw new DukeException("Task with specified number does not exist.");
        }
        Task removedTask = tasks.remove(taskNumber - 1);
        System.out.println("Deleted: " + removedTask);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }
    public void markTask(int taskNumber) throws DukeException {
        if (taskNumber < 1 || taskNumber > tasks.size()) {
            throw new DukeException("Task with specified number does not exist.");
        }
        Task task = tasks.get(taskNumber - 1);
        task.markAsDone();
        System.out.println("Marked as done: " + task);
    }
    public void unmarkTask(int taskNumber) throws DukeException {
        if (taskNumber < 1 || taskNumber > tasks.size()) {
            throw new DukeException("Task with specified number does not exist.");
        }
        Task task = tasks.get(taskNumber - 1);
        task.unmarkAsDone();
        System.out.println("Unmarked as done: " + task);
    }
    public void listTasks() {
        if (tasks.isEmpty()) {
            System.out.println("Your task list is empty.");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        }
    }
    public List<Task> getTasks() {
        return this.tasks;
    }
}
