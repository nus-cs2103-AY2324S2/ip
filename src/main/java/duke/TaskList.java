package duke;
import java.util.ArrayList;
import java.util.List;

/**
 * The TaskList class represents a collection of tasks and provides methods to manipulate the tasks.
 */
public class TaskList {
    private List<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with the given list of tasks.
     *
     * @param tasks The list of tasks to initialize the TaskList.
     * @throws DukeDataCorruptedException If there is an issue decoding the task data.
     */
    public TaskList(List<Task> tasks) throws DukeDataCorruptedException {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the TaskList.
     *
     * @param task The task to add.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task from the TaskList based on its index.
     *
     * @param index The index of the task to delete.
     */
    public void deleteTask(int index) {
        tasks.remove(index);
    }

    /**
     * Retrieves a task from the TaskList based on its index.
     *
     * @param index The index of the task to retrieve.
     * @return The task at the specified index.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Marks a task in the TaskList as done based on its index.
     *
     * @param index The index of the task to mark as done.
     */
    public void markTaskAsDone(int index) {
        tasks.get(index).setDone(true);
    }

    /**
     * Marks a task in the TaskList as not done based on its index.
     *
     * @param index The index of the task to mark as not done.
     */
    public void markTaskAsNotDone(int index) {
        tasks.get(index).setDone(false);
    }

    /**
     * Retrieves the list of tasks in the TaskList.
     *
     * @return The list of tasks.
     */
    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Finds tasks in the TaskList that contain the specified keyword in their description.
     *
     * @param keyword The keyword to search for in task descriptions.
     * @return A string representation of tasks matching the keyword.
     */
    public String findTasksByKeyword(String keyword) {
        List<Task> matchingTasks = new ArrayList<>();

        for (Task task : tasks) {
            if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                matchingTasks.add(task);
            }
        }

        return getTasksMessage(matchingTasks, "Tasks found with keyword '" + keyword + "':\n");
    }

    /**
     * Generates a message for displaying a list of tasks.
     *
     * @param taskList The list of tasks to be displayed.
     * @param header   The header message to be included before the tasks.
     * @return A string message displaying the list of tasks.
     */
    private String getTasksMessage(List<Task> taskList, String header) {
        StringBuilder message = new StringBuilder(header);

        if (taskList.isEmpty()) {
            message.append("No matching tasks found.\n");
        } else {
            for (int i = 0; i < taskList.size(); i++) {
                message.append((i + 1) + ". " + taskList.get(i) + "\n");
            }
        }

        return message.toString();
    }
}

