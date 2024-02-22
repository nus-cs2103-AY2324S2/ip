package cruisey.task;

import java.util.ArrayList;

import cruisey.exception.CruiseyException;
import cruisey.ui.Ui;

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
     * @throws CruiseyException If the index is out of bounds.
     */
    public String deleteTask(int index) throws CruiseyException {
        assert index >= 0 && index < tasks.size() : "Index should be within bounds.";
        Task removedTask = tasks.remove(index);
        return this.ui.printDeletedTaskMessage(removedTask);
    }

    /**
     * Marks a task as done and prints a message.
     *
     * @param job The task to be marked as done.
     * @throws CruiseyException If the task is already marked as done.
     */
    public String markStatus(Task job) throws CruiseyException {
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
     * @throws CruiseyException If the task is already marked as not done.
     */
    public String unmarkStatus(Task job) throws CruiseyException {
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
     * @throws CruiseyException If there is an issue displaying the task list.
     */
    public static String getList(TaskList taskList) throws CruiseyException {
        assert taskList != null : "TaskList to be displayed should not be null";
        StringBuilder taskDetails = new StringBuilder();

        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.getTask(i);
            appendTaskDetails(taskDetails, i + 1, task);
        }

        return Ui.printMessage(taskDetails.toString());
    }

    /**
     * Appends the formatted details of a task to a StringBuilder, including task type, status icon,
     * and description. If the task is an instance of Deadline or Event, additional details are appended.
     *
     * @param taskDetails StringBuilder to append the formatted task details.
     * @param index       Index of the task in the list.
     * @param task        The Task object to extract details from.
     */
    private static void appendTaskDetails(StringBuilder taskDetails, int index, Task task) {
        taskDetails.append(index).append(".[").append(task.getType()).append("][");

        if (task != null) {
            taskDetails.append(task.getStatusIcon()).append("] ").append(task.getDescription());
        }

        if (task instanceof Deadline) {
            appendDeadlineDetails(taskDetails, (Deadline) task);
        } else if (task instanceof Event) {
            appendEventDetails(taskDetails, (Event) task);
        }

        taskDetails.append(appendPriority(task)).append("\n");
    }

    /**
     * Appends the formatted details of a Deadline task to a StringBuilder, including the deadline.
     *
     * @param taskDetails    StringBuilder to append the formatted deadline details.
     * @param deadlineTask   The Deadline task object to extract details from.
     */
    private static void appendDeadlineDetails(StringBuilder taskDetails, Deadline deadlineTask) {
        taskDetails.append(" (by: ").append(deadlineTask.getBy()).append(")");
    }

    /**
     * Appends the formatted details of an Event task to a StringBuilder, including the event's start
     * and end times.
     *
     * @param taskDetails    StringBuilder to append the formatted event details.
     * @param eventTask      The Event task object to extract details from.
     */
    private static void appendEventDetails(StringBuilder taskDetails, Event eventTask) {
        taskDetails.append(" (from: ").append(eventTask.getFrom()).append(" to: ")
                .append(eventTask.getTo()).append(")");
    }

    /**
     * Appends the priority details to a task's formatted string representation.
     *
     * This method checks if the task has a priority assigned and, if so, appends the priority information to the
     * formatted string. If no priority is assigned, an empty string is returned.
     *
     * @param task The task for which priority details need to be appended.
     * @return A string containing the priority details if available, or an empty string otherwise.
     */
    private static String appendPriority(Task task) {
        return (task.getPriority() != null) ? " -> Priority: " + task.getPriority() : "";
    }

    /**
     * Searches for tasks within a given list that contain a specified search string in their descriptions.
     * Creates and returns a new TaskList containing the found tasks.
     *
     * @param searchString The string to search for in the task descriptions.
     * @return A new TaskList containing tasks with descriptions that match the search string.
     */
    public TaskList findTask(String searchString) throws CruiseyException {
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
