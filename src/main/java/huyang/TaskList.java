package huyang;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Represents a collection of tasks in a task list.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructs a TaskList with the given list of tasks.
     *
     * @param tasks The list of tasks to initialize the TaskList with.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Retrieves the list of tasks stored in this TaskList.
     *
     * @return An ArrayList containing the tasks in the TaskList.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Gets the number of tasks in the TaskList.
     *
     * @return The number of tasks in the TaskList.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Gets a task at the specified index in the TaskList.
     *
     * @param index The index of the task to retrieve.
     * @return The task at the specified index.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Adds a new task to the TaskList based on the input command and type.
     *
     * @param input       The input string describing the task.
     * @param commandType The type of command associated with the input.
     * @return The added task.
     * @throws TaskException If there is an issue creating or adding the task.
     */
    public Task addTask(String input, Parser.CommandType commandType) throws TaskException {
        Task task = TaskFactory.createTask(input, commandType);
        tasks.add(task);
        return task;
    }

    /**
     * Marks or unmarks a task in the TaskList as done based on the input.
     *
     * @param input  The input string specifying the task.
     * @param isDone A boolean indicating whether the task should be marked as done or not.
     * @return The modified task.
     * @throws TaskException If there is an issue marking or unmarking the task.
     */
    public Task markOrUnmarkTask(String input, boolean isDone) throws TaskException {
        Task task = getTaskByNumber(input);
        task.setDone(isDone);
        return task;
    }

    /**
     * Deletes a task from the TaskList based on the input.
     *
     * @param input The input string specifying the task to delete.
     * @return The deleted task.
     * @throws TaskException If there is an issue deleting the task.
     */
    public Task deleteTask(String input) throws TaskException {
        Task task = getTaskByNumber(input);
        tasks.remove(task);
        return task;
    }

    /**
     * Finds tasks in the TaskList that contain the specified keyword.
     *
     * @param keyword The keyword to search for in task names.
     * @return An ArrayList of tasks matching the keyword.
     */
    public ArrayList<Task> findTasks(String keyword) {
        return tasks.stream()
                .filter(task -> task.getTaskName().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    private Task getTaskByNumber(String input) throws TaskException {
        try {
            int taskNumber = Integer.parseInt(input.split(" ")[1]);
            if (taskNumber < 1 || taskNumber > tasks.size()) {
                throw TaskException.forInvalidTaskNumber();
            }
            return tasks.get(taskNumber - 1);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw TaskException.forInvalidTaskNumber();
        }
    }
}
