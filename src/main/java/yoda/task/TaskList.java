package yoda.task;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Represents a list of tasks. It provides operations to add, delete, and retrieve tasks.
 */
public class TaskList {
    private final List<Task> TASKS; // List to store the tasks

    public TaskList() {
        this.TASKS = new ArrayList<>();
    }

    public TaskList(List<Task> loadedTasks) {
        this.TASKS = loadedTasks != null ? new ArrayList<>(loadedTasks) : new ArrayList<>();
    }
    /**
     * Adds a task to the list.
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        if (task != null) {
            TASKS.add(task);
        }
    }

    public void findTasks(String keyword) {
        List<Task> foundTasks = new ArrayList<>();
        for (Task task : TASKS) {
            if (task.getDescription().contains(keyword)) {
                foundTasks.add(task);
            }
        }
        if (foundTasks.isEmpty()) {
            System.out.println("No tasks found with the keyword: " + keyword);
        } else {
            System.out.println("Tasks found with the keyword: " + keyword);
            for (int i = 0; i < foundTasks.size(); i++) {
                System.out.println(i + 1 + "." + foundTasks.get(i));
            }
        }
    }

    /**
     * Deletes a task from the list.
     * @param taskNumber The number of the task to be deleted.
     * @throws Exception If the task number is invalid.
     */
    public void deleteTask(int taskNumber) throws Exception {
        if (taskNumber <= 0 || taskNumber > TASKS.size()) {
            throw new Exception("Valid task number, provide you must.");
        }
        TASKS.remove(taskNumber - 1);
    }

    /**
     * Marks a task as done.
     * @param taskNumber The number of the task to mark as done.
     * @throws Exception If the task number is invalid.
     */
    public void markTaskAsDone(int taskNumber) throws Exception {
        if (taskNumber <= 0 || taskNumber > TASKS.size()) {
            throw new Exception("Valid task number, provide you must.");
        }
        TASKS.get(taskNumber - 1).markAsDone();
    }

    /**
     * Marks a task as undone.
     * @param taskNumber The number of the task to mark as not done.
     * @throws Exception If the task number is invalid.
     */
    public void markTaskAsUndone(int taskNumber) throws Exception {
        if (taskNumber <= 0 || taskNumber > TASKS.size()) {
            throw new Exception("Valid task number, provide you must.");
        }
        TASKS.get(taskNumber - 1).markAsUndone();
    }

    /**
     * Retrieves a task from the list.
     * @param taskNumber The number of the task to retrieve.
     * @return The task at the specified position.
     * @throws Exception If the task number is invalid.
     */
    public Task getTask(int taskNumber) throws Exception {
        if (taskNumber <= 0 || taskNumber > TASKS.size()) {
            throw new Exception("Valid task number, provide you must.");
        }
        return TASKS.get(taskNumber - 1);
    }

    /**
     * Returns the number of tasks in the list.
     * @return The number of tasks in the list.
     */
    public int size() {
        return TASKS.size();
    }

    /**
     * Checks if the list is empty.
     * @return true if the list is empty, false otherwise.
     */
    public boolean isEmpty() {
        return TASKS.isEmpty();
    }

    /**
     * Returns a stream of tasks.
     * @return A stream of Task objects.
     */
    public Stream<Task> stream() {
        return TASKS.stream();
    }

    @Override
    public String toString() {
        if (TASKS.isEmpty()) {
            return "Empty, your task list is.";
        }

        StringBuilder response = new StringBuilder("Tasks in your list, here they are:\n");
        for (int i = 0; i < TASKS.size(); i++) {
            response.append(i + 1).append(".").append(TASKS.get(i)).append("\n");
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
