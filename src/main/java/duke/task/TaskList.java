package duke.task;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import duke.DukeException;


/**
 * Represents a list of tasks.
 */
public class TaskList {
    private final List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to add.
     * @return A message indicating the task has been added and the total number of tasks.
     */
    public String addTask(Task task) {
        tasks.add(task);
        return "Added: " + task + "\nNow you have " + tasks.size() + " tasks in the list.";
    }

    /**
     * Deletes a task from the task list.
     *
     * @param taskNumber The index of the task to delete.
     * @return A message indicating the task has been deleted and the total number of tasks.
     * @throws DukeException if the task number is invalid.
     */
    public String deleteTask(int taskNumber) throws DukeException {
        validateTaskNumber(taskNumber);
        Task removedTask = tasks.remove(taskNumber - 1);
        return "Deleted: " + removedTask + "\nNow you have " + tasks.size() + " tasks in the list.";
    }

    /**
     * Marks a task as done.
     *
     * @param taskNumber The index of the task to mark as done.
     * @return A message indicating the task has been marked as done.
     * @throws DukeException if the task number is invalid.
     */
    public String markTask(int taskNumber) throws DukeException {
        validateTaskNumber(taskNumber);
        Task task = tasks.get(taskNumber - 1);
        task.markAsDone();
        return "Marked as done: " + task;
    }

    /**
     * Marks a task as undone.
     *
     * @param taskNumber The index of the task to mark as undone.
     * @return A message indicating the task has been marked as undone.
     * @throws DukeException if the task number is invalid.
     */
    public String unmarkTask(int taskNumber) throws DukeException {
        validateTaskNumber(taskNumber);
        Task task = tasks.get(taskNumber - 1);
        task.unmarkAsDone();
        return "Unmarked as done: " + task;
    }


    /**
     * Lists all tasks in the task list.
     *
     * @return A string representation of all tasks in the task list.
     */
    public String listTasks() {
        if (tasks.isEmpty()) {
            return "Your task list is empty.";
        } else {
            StringBuilder sb = new StringBuilder("Here are the tasks in your list:\n");
            for (int i = 0; i < tasks.size(); i++) {
                sb.append(i + 1).append(". ").append(tasks.get(i)).append("\n");
            }
            return sb.toString();
        }
    }

    /**
     * Finds tasks containing the specified keyword.
     *
     * @param keyword The keyword to search for.
     * @return A string representation of tasks matching the keyword.
     */
    public String findTask(String keyword) {
        List<Task> filteredTasks = tasks.stream()
            .filter(task -> task.getDescription().contains(keyword))
            .collect(Collectors.toList());

        if (filteredTasks.isEmpty()) {
            return "No tasks match your search criteria.";
        } else {
            StringBuilder sb = new StringBuilder("Here are the matching tasks in your list:\n");
            for (int i = 0; i < filteredTasks.size(); i++) {
                sb.append(i + 1).append(". ").append(filteredTasks.get(i)).append("\n");
            }
            return sb.toString();
        }
    }

    private void validateTaskNumber(int taskNumber) throws DukeException {
        if (taskNumber < 1 || taskNumber > tasks.size()) {
            throw new DukeException("Task does not exist.");
        }
    }

    public List<Task> getTasks() {
        return tasks;
    }
}
