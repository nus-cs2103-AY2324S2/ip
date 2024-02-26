package util;

import exceptions.ChatBotException;
import tasks.Task;
import java.util.ArrayList;
import java.util.List;

/**
 * Manages the list of tasks and provides operations to manipulate the tasks.
 */
public class TaskList {
    private static final int MAX_TASKS = 100;
    private List<Task> tasks;

    /**
     * Constructs a new TaskList object.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a new TaskList object with the given list of tasks.
     *
     * @param tasks The list of tasks to initialize the TaskList with.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    /**
     * Retrieves the list of tasks.
     *
     * @return The list of tasks.
     */
    public List<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Retrieves the task at the specified index.
     *
     * @param index The index of the task to retrieve.
     * @return The task at the specified index.
     */
    public Task getTaskIndex(int index) {
        return this.tasks.get(index);
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        assert task != null : "Oops! Task to add cannot be null.";
        assert this.tasks.size() < MAX_TASKS : "Oops! Task list is full.";
        this.tasks.add(task);
    }

    /**
     * Deletes a task from the task list.
     *
     * @param number The index of the task to be deleted.
     * @return The deleted task.
     * @throws ChatBotException If the task list is empty or the specified index is invalid.
     */
    public Task deleteTask(int number) throws ChatBotException {
        assert number > 0 : "Oops! Task number cannot be less than 1.";
        if (this.tasks.size() == 0) {
            throw new ChatBotException("Oops! There are no tasks in the list.");
        }
        return this.tasks.remove(number - 1);
    }

    /**
     * Retrieves a summary message about the number of tasks in the list.
     *
     * @return A summary message about the number of tasks in the list.
     */
    public String getTaskSummary() {
        return "\tNow you have " + this.tasks.size() + " task"
                + (this.tasks.size() == 1 ? "" : "s") + " in the list";
    }

    /**
     * Marks a task in the task list as done.
     *
     * @param index The index of the task to be marked as done.
     * @throws ChatBotException If the specified index is invalid (zero or negative).
     */
    public void markTask(int index) throws ChatBotException {
        assert index > 0 : "Oops! Task number cannot be less than 1.";
        if (this.tasks.size() == 0) {
            throw new ChatBotException("Oops! There are no tasks in the list.");
        }
        Task currTask = this.tasks.get(index - 1);
        currTask.markAsDone();
    }

    /**
     * Marks a task in the task list as not done.
     *
     * @param index The index of the task to be marked as not done.
     * @throws ChatBotException If the specified index is invalid (zero or negative).
     */
    public void unmarkTask(int index) throws ChatBotException {
        assert index > 0 : "Oops! Task number cannot be less than 1.";
        if (index <= 0) {
            throw new ChatBotException("Oops! Number entered cannot be zero or negative.");
        }
        if (this.tasks.size() == 0) {
            throw new ChatBotException("Oops! There are no tasks in the list.");
        }
        Task currTask = this.tasks.get(index - 1);
        currTask.markAsNotDone();
    }

    /**
     * Retrieves a string representation of all tasks in the task list.
     *
     * @return A string representation of all tasks in the task list.
     */
    public String listTasks() {
        if (this.tasks.size() == 0) {
            return "\tThe task list is empty.";
        } else {
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < this.tasks.size(); i++) {
                Task currTask = this.tasks.get(i);
                result.append("\t").append(i + 1).append(".").append(currTask.toString()).append("\n");
            }
            return result.toString();
        }
    }

    /**
     * Finds tasks containing a specified keyword in their descriptions.
     *
     * @param keyWord The keyword to search for in task descriptions.
     * @return A TaskList containing tasks matching the keyword.
     * @throws ChatBotException If no tasks match the keyword.
     */
    public TaskList findTasks(String keyWord) throws ChatBotException {
        assert keyWord != null && !keyWord.isEmpty() : "Oops! Keyword must not be null or empty.";
        List<Task> matchingTasks = new ArrayList<>();
        boolean isFound = false;
        for (Task task : tasks) {
            if (task.getDescription().contains(keyWord)) {
                matchingTasks.add(task);
                isFound = true;
            }
        }
        if (!isFound) {
            throw new ChatBotException("No matching tasks found.");
        }
        return new TaskList(matchingTasks);
    }
}

