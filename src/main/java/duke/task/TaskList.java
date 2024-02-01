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
    public TaskList(ArrayList < Task > tasks) {
        this.tasks = tasks;
    }

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList < > ();
    }
    /**
     * Adds a generic task to the task list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Adds a ToDo task to the task list and prints its details.
     *
     * @param task The ToDo task to be added.
     */
    public void addToDoTask(ToDo task) {
        tasks.add(task);
        System.out.println(task.toString());
    }

    /**
     * Adds a Deadline task to the task list and prints its details.
     *
     * @param task The Deadline task to be added.
     */
    public void addDeadlineTask(Deadline task) {
        tasks.add(task);
        System.out.println(task.toString());
    }


    /**
     * Adds an Event task to the task list and prints its details.
     *
     * @param task The Event task to be added.
     */
    public void addEventTask(Event task) {
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
    public void deleteTask(int index) throws DukeException {
        if (index < 0 || index >= tasks.size()) {
            throw new DukeException("You have not created task " + (index + 1) + " for me to delete!");
        }
        Task removedTask = tasks.remove(index);
        ui.printDeletedTaskMessage(removedTask);
    }

    /**
     * Marks a task as done and prints a message.
     *
     * @param job The task to be marked as done.
     * @throws DukeException If the task is already marked as done.
     */
    public void markStatus(Task job) throws DukeException {
        if (job.isDone) {
            throw new DukeException("This task is already marked as done.");
        }
        job.isDone = true;
        ui.markTask(job);
    }

    /**
     * Unmarks a task as done and prints a message.
     *
     * @param job The task to be unmarked.
     * @throws DukeException If the task is already marked as not done.
     */
    public void unmarkStatus(Task job) throws DukeException {
        if (!job.isDone) {
            throw new DukeException("This task is already marked as not done.");
        }
        job.isDone = false;
        ui.unmarkTask(job);
    }

    /**
     * Displays the list of tasks along with their details.
     *
     * @param taskList The TaskList to be displayed.
     * @throws DukeException If there is an issue displaying the task list.
     */
    public static void getList(TaskList taskList) throws DukeException { //need to put in UI class?
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.getTask(i);
            String taskDetails = (i + 1) + ".[" + task.type + "][" + (task != null ? task.getStatusIcon() : "") + "] " + task.description;
            if (task instanceof Deadline) {
                Deadline deadlineTask = (Deadline) task;
                taskDetails += " (by: " + (deadlineTask.by == null ? deadlineTask.byString : deadlineTask.by) + ")";
            } else if (task instanceof Event) {
                Event eventTask = (Event) task;
                taskDetails += " (from: " + (eventTask.from == null ? eventTask.fromString : eventTask.from) + " to: " +
                        (eventTask.to == null ? eventTask.toString : eventTask.to) + ")";
            }
            System.out.println(taskDetails);
        }
    }

    /**
     * Searches for tasks within a given list that contain a specified search string in their descriptions.
     * Creates and returns a new TaskList containing the found tasks.
     *
     * @param searchString The string to search for in the task descriptions.
     * @return A new TaskList containing tasks with descriptions that match the search string.
     */
    public TaskList findTask(String searchString) throws DukeException {
        TaskList foundTasks = new TaskList();

        for (Task job: tasks) {
            if (job.getDescription().contains(searchString)) {
                foundTasks.addTask(job);
            }
        }
        return foundTasks;
    }

}