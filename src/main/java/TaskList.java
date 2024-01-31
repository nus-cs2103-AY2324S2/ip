import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private final List<Task> tasks; // List to store the tasks

    public TaskList(List<Task> loadedTasks) {
        this.tasks = loadedTasks != null ? new ArrayList<>(loadedTasks) : new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void deleteTask(int taskNumber) throws Exception {
        if (taskNumber <= 0 || taskNumber > tasks.size()) {
            throw new Exception("Valid task number, provide you must.");
        }
        tasks.remove(taskNumber - 1);
    }

    public void markTaskAsDone(int taskNumber) throws Exception {
        if (taskNumber <= 0 || taskNumber > tasks.size()) {
            throw new Exception("Valid task number, provide you must.");
        }
        tasks.get(taskNumber - 1).markAsDone();
    }

    public void markTaskAsUndone(int taskNumber) throws Exception {
        if (taskNumber <= 0 || taskNumber > tasks.size()) {
            throw new Exception("Valid task number, provide you must.");
        }
        tasks.get(taskNumber - 1).markAsUndone();
    }

    public Task getTask(int taskNumber) throws Exception {
        if (taskNumber <= 0 || taskNumber > tasks.size()) {
            throw new Exception("Valid task number, provide you must.");
        }
        return tasks.get(taskNumber - 1);
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public int size() {
        return tasks.size();
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    @Override
    public String toString() {
        if (tasks.isEmpty()) {
            return "Empty, your task list is.";
        }

        StringBuilder response = new StringBuilder("Tasks in your list, here they are:\n");
        for (int i = 0; i < tasks.size(); i++) {
            response.append(i + 1).append(".").append(tasks.get(i)).append("\n");
        }
        return response.toString().trim();
    }


    /**
     * Prints a message wrapped with lines for better readability.
     * @param message The message to be printed.
     */
    public void printMessage(String message) {
        printLine();
        System.out.println(message);
        printLine();
    }

    /**
     * Prints a line for visual separation in the console output.
     */
    private void printLine() {
        System.out.println("________________________________________________________");
    }
}
