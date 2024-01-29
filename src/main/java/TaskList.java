import java.util.ArrayList;
import java.util.List;

public class TaskList {
    // List of tasks to be done by the user
    private ArrayList<Task> tasks;
    private int taskCount;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void addTodo(String description) {
        this.tasks.add(new Todo(description));
        taskCount++;
    }

    public void addDeadline(String description, String dueDate) {
        this.tasks.add(new Deadline(description, dueDate));
        taskCount++;
    }

    public void addEvent(String description, String startTime, String endTime) {
        this.tasks.add(new TimeBlock(description, startTime, endTime));
        taskCount++;
    }

    // Method to return task by index
    public Task getTaskByNum(int taskNumber) {
        // System.out.println("Task Number: " + taskNumber + "at index: " + (taskNumber
        // - 1));
        return tasks.get(taskNumber - 1);
    }

    // Method to remove task
    public void removeTask(int taskNumber) {
        tasks.remove(taskNumber - 1);
        taskCount--;
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

    // Method to return the number of tasks
    public int getTaskCount() {
        return taskCount;
    }
}