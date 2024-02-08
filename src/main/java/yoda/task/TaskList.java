package yoda.task;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class TaskList {
    private final List<Task> TASKS; // List to store the tasks

    public TaskList(List<Task> loadedTasks) {
        this.TASKS = loadedTasks != null ? new ArrayList<>(loadedTasks) : new ArrayList<>();
    }

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

    public void deleteTask(int taskNumber) throws Exception {
        if (taskNumber <= 0 || taskNumber > TASKS.size()) {
            throw new Exception("Valid task number, provide you must.");
        }
        TASKS.remove(taskNumber - 1);
    }

    public void markTaskAsDone(int taskNumber) throws Exception {
        if (taskNumber <= 0 || taskNumber > TASKS.size()) {
            throw new Exception("Valid task number, provide you must.");
        }
        TASKS.get(taskNumber - 1).markAsDone();
    }

    public void markTaskAsUndone(int taskNumber) throws Exception {
        if (taskNumber <= 0 || taskNumber > TASKS.size()) {
            throw new Exception("Valid task number, provide you must.");
        }
        TASKS.get(taskNumber - 1).markAsUndone();
    }

    public Task getTask(int taskNumber) throws Exception {
        if (taskNumber <= 0 || taskNumber > TASKS.size()) {
            throw new Exception("Valid task number, provide you must.");
        }
        return TASKS.get(taskNumber - 1);
    }


    public int size() {
        return TASKS.size();
    }

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
