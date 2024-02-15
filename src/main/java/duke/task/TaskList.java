package duke.task;

import java.util.ArrayList;
import java.util.List;

import duke.DukeException;

/**
 * The TaskList class represents a collection of tasks in the Duke application.
 * It provides methods to manipulate the list of tasks, such as adding, deleting,
 * marking as done, listing, and finding tasks.
 */
public class TaskList {
    private List<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with the specified list of tasks.
     *
     * @param tasks The list of tasks to initialize the TaskList with.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
        System.out.println("Added: " + task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    /**
     * Deletes a task from the task list.
     *
     * @param taskNumber The number of the task to be deleted.
     * @throws DukeException If the specified task number is out of range.
     */
    public void deleteTask(int taskNumber) throws DukeException {
        if (taskNumber < 1 || taskNumber > tasks.size()) {
            throw new DukeException("duke.task.Task with specified number does not exist.");
        }
        Task removedTask = tasks.remove(taskNumber - 1);
        System.out.println("Deleted: " + removedTask);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    /**
     * Marks a task as done.
     *
     * @param taskNumber The number of the task to be marked as done.
     * @throws DukeException If the specified task number is out of range.
     */
    public void markTask(int taskNumber) throws DukeException {
        if (taskNumber < 1 || taskNumber > tasks.size()) {
            throw new DukeException("duke.task.Task with specified number does not exist.");
        }
        Task task = tasks.get(taskNumber - 1);
        task.markAsDone();
        System.out.println("Marked as done: " + task);
    }

    /**
     * Marks a task as not done.
     *
     * @param taskNumber The number of the task to be unmarked as done.
     * @throws DukeException If the specified task number is out of range.
     */
    public void unmarkTask(int taskNumber) throws DukeException {
        if (taskNumber < 1 || taskNumber > tasks.size()) {
            throw new DukeException("duke.task.Task with specified number does not exist.");
        }
        Task task = tasks.get(taskNumber - 1);
        task.unmarkAsDone();
        System.out.println("Unmarked as done: " + task);
    }

    /**
     * Lists all tasks in the task list.
     */
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


    /**
     * Finds and displays tasks containing the specified keyword in their description.
     *
     * @param keyword The keyword to search for in task descriptions.
     */
    public void findTask(String keyword) {
        System.out.println("____________________________________________________________");
        System.out.println("Here are the matching tasks in your list:");

        int taskNumber = 1;
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                System.out.println(taskNumber++ + "." + task);
            }
        }

        System.out.println("____________________________________________________________");
    }

    /**
     * Gets the list of tasks.
     *
     * @return The list of tasks.
     */
    public List<Task> getTasks() {
        return this.tasks;
    }
}
