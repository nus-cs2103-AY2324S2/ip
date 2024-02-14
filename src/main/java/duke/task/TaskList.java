package duke.task;

import duke.exception.DukeException;
import duke.ui.Ui;

import java.util.ArrayList;

/**
 * The TaskList class represents a list of tasks in the Duke application.
 * It provides methods to add, delete, mark, unmark, and display tasks.
 */
public class TaskList {
    /** The list of tasks. */
    private ArrayList < Task > tasks;

    /** The user interface for displaying messages related to task operations. */
    private Ui ui;

    /**
     * Constructs a TaskList with the specified list of tasks.
     *
     * @param tasks The list of tasks.
     */
    public TaskList(ArrayList<Task> tasks, Ui ui) {
        this.tasks = tasks;
        this.ui = ui;
        assert this.tasks != null : "Task list should not be null.";
        assert this.ui != null : "UI should not be null.";
    }

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
        assert this.tasks != null : "Task list should not be null.";
    }

    /**
     * Adds a generic task to the task list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        assert task != null : "Task to be added should not be null.";
        tasks.add(task);
    }

    /**
     * Adds a ToDo task to the task list and prints its details.
     *
     * @param task The ToDo task to be added.
     */
    public void addToDoTask(ToDo task) {
        assert task != null : "ToDo task to be added should not be null.";
        tasks.add(task);
        System.out.println(task.toString());
    }

    /**
     * Adds a Deadline task to the task list and prints its details.
     *
     * @param task The Deadline task to be added.
     */
    public void addDeadlineTask(Deadline task) {
        assert task != null : "Deadline task to be added should not be null.";
        tasks.add(task);
        System.out.println(task.toString());
    }

    /**
     * Adds an Event task to the task list and prints its details.
     *
     * @param task The Event task to be added.
     */
    public void addEventTask(Event task) {
        assert task != null : "Event task to be added should not be null.";
        tasks.add(task);
        System.out.println(task.toString());
    }

    /**
     * Retrieves a task from the task list based on the specified index.
     *
     * @param index The index of the task in the list.
     * @return The task at the specified index.
     */
    public Task getTask(int index) {
        assert index >= 0 && index < tasks.size() : "Index should be within bounds.";
        return tasks.get(index);
    }

    /**
     * Gets the number of tasks in the task list.
     *
     * @return The number of tasks in the task list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Deletes a task from the task list based on the specified index.
     *
     * @param index The index of the task to be deleted.
     * @throws DukeException If the index is out of bounds.
     */
    public String deleteTask(int index) throws DukeException {
        assert index >= 0 && index < tasks.size() : "Index should be within bounds.";
        Task removedTask = tasks.remove(index);
        return this.ui.printDeletedTaskMessage(removedTask);
    }

    /**
     * Marks a task as done and prints a message.
     *
     * @param job The task to be marked as done.
     * @throws DukeException If the task is already marked as done.
     */
    public String markStatus(Task job) throws DukeException {
        assert job != null : "Task to be marked should not be null.";
        if (job.isDone) {
            return Ui.showError("This task is already marked as done.");
        }
        job.isDone = true;
        return this.ui.markTask(job);
    }

    /**
     * Unmarks a task as done and prints a message.
     *
     * @param job The task to be unmarked.
     * @throws DukeException If the task is already marked as not done.
     */
    public String unmarkStatus(Task job) throws DukeException {
        assert job != null : "Task to be unmarked should not be null.";
        if (!job.isDone) {
            return Ui.showError("This task is already marked as not done.");
        }
        job.isDone = false;
        return this.ui.unmarkTask(job);
    }

    /**
     * Displays the list of tasks along with their details.
     *
     * @param taskList The TaskList to be displayed.
     * @throws DukeException If there is an issue displaying the task list.
     */
    public static String getList(TaskList taskList) throws DukeException {
        assert taskList != null : "TaskList to be displayed should not be null.";
        String taskDetails = "";
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.getTask(i);
            taskDetails += (i + 1) + ".[" + task.type + "][" + (task != null ? task.getStatusIcon() : "") + "] " + task.description;
            if (task instanceof Deadline) {
                Deadline deadlineTask = (Deadline) task;
                taskDetails += " (by: " + (deadlineTask.by == null ? deadlineTask.byString : deadlineTask.by) + ")";
            } else if (task instanceof Event) {
                Event eventTask = (Event) task;
                taskDetails += " (from: " + (eventTask.from == null ? eventTask.fromString : eventTask.from) + " to: " +
                        (eventTask.to == null ? eventTask.toString : eventTask.to) + ")";
            }
            taskDetails += "\n";
        }
        return Ui.printMessage(taskDetails);
    }

    /**
     * Searches for tasks within a given list that contain a specified search string in their descriptions.
     * Creates and returns a new TaskList containing the found tasks.
     *
     * @param searchString The string to search for in the task descriptions.
     * @return A new TaskList containing tasks with descriptions that match the search string.
     */
    public TaskList findTask(String searchString) throws DukeException {
        assert searchString != null : "Search string should not be null.";
        TaskList foundTasks = new TaskList();

        for (Task job : tasks) {
            if (job.getDescription().contains(searchString)) {
                foundTasks.addTask(job);
            }
        }
        return foundTasks;
    }

}