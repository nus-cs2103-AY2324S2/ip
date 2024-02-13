package yoda.task;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import yoda.constants.Replies;
import yoda.exceptions.InvalidTaskException;

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

    /**
     * Finds a task from the list.
     * @param keyword The keyword to search for in the task descriptions.
     * @return A string containing the tasks found with the keyword.
     */
    public String findTasks(String keyword) {
        List<Task> foundTasks = new ArrayList<>();
        for (Task task : TASKS) {
            if (task.getDescription().contains(keyword)) {
                foundTasks.add(task);
            }
        }

        StringBuilder response = new StringBuilder();
        if (foundTasks.isEmpty()) {
            response.append("No tasks found with the keyword: ").append(keyword);
        } else {
            response.append("Tasks found with the keyword: ").append(keyword).append("\n");
            for (int i = 0; i < foundTasks.size(); i++) {
                response.append(i + 1).append(".").append(foundTasks.get(i).toString()).append("\n");
            }
        }
        return response.toString();
    }


    /**
     * Deletes a task from the list.
     * @param taskNumber The number of the task to be deleted.
     * @throws Exception If the task number is invalid.
     */
    public String deleteTask(int taskNumber) throws InvalidTaskException {
        if (taskNumber <= 0 || taskNumber > TASKS.size()) {
            throw new InvalidTaskException();
        }
        TASKS.remove(taskNumber - 1);
        return "Removed, this task has been:\nNow you have " + this.getSize() + " tasks in the list.";
    }

    /**
     * Marks a task as done.
     * @param taskNumber The number of the task to mark as done.
     * @throws InvalidTaskException If the task number is invalid.
     */
    public String markTaskAsDone(int taskNumber) throws InvalidTaskException {
        if (taskNumber <= 0 || taskNumber > TASKS.size()) {
            throw new InvalidTaskException();
        }
        try {
            Task task = this.getTask(taskNumber);
            task.markAsDone();
            return Replies.TASK_MARKED_DONE + "\n" + task;
        } catch (InvalidTaskException e) {
            return e.getMessage();
        }
    }

    /**
     * @param taskNumber The number of the task to mark as not done.
     * @return The response to marking a task as undone.
     * @throws InvalidTaskException If the task number is invalid.
     */
    public String markTaskAsUndone(int taskNumber) throws InvalidTaskException {
        if (taskNumber <= 0 || taskNumber > TASKS.size()) {
            throw new InvalidTaskException();
        }
        try {
            Task task = this.getTask(taskNumber);
            task.markAsUndone();
            return Replies.TASK_MARKED_UNDONE + "\n" + task;
        } catch (InvalidTaskException e) {
            return e.getMessage();
        }
    }

    /**
     * Retrieves a task from the list.
     * @param taskNumber The number of the task to retrieve.
     * @return The task at the specified position.
     * @throws InvalidTaskException If the task number is invalid.
     */
    public Task getTask(int taskNumber) throws InvalidTaskException {
        if (taskNumber <= 0 || taskNumber > TASKS.size()) {
            throw new InvalidTaskException();
        }
        return TASKS.get(taskNumber - 1);
    }

    /**
     * Returns the number of tasks in the list.
     * @return The number of tasks in the list.
     */
    public int getSize() {
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

}
