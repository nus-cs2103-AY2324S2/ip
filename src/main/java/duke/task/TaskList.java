package duke.task;

import duke.storage.Storage;
import duke.exception.DukeException;

import java.util.ArrayList;

/**
 * The TaskList class represents a list of tasks in Duke.
 */
public class TaskList {

    /**
     * The ArrayList that stores the tasks.
     */
    private final ArrayList<Task> tasks;

    /**
     * Constructs a TaskList with the given ArrayList of tasks.
     *
     * @param tasks The ArrayList of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Saves the tasks in the TaskList to the provided Storage.
     *
     * @param storage The Storage object to save tasks to.
     * @throws DukeException If there is an issue saving tasks to storage.
     */
    public void saveToStorage(Storage storage) throws DukeException {
        storage.saveTasks(tasks);
    }

    /**
     * Retrieves the ArrayList of tasks from the TaskList.
     *
     * @return The ArrayList of tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Adds a task to the TaskList and returns a formatted message.
     *
     * @param t The task to be added.
     * @return A message confirming the addition of the task.
     */
    public String addTask(Task t){
        tasks.add(t);
        return "Got it. I've added this task:\n" + "   " + t + "\n"
                + " Now you have " + tasks.size() + " tasks in the list.";
    }

    /**
     * Deletes a task from the TaskList by index and returns a formatted message.
     *
     * @param taskIndex The index of the task to be deleted.
     * @return A message confirming the deletion of the task.
     * @throws DukeException If the specified task index is out of bounds.
     */
    public String deleteTask(int taskIndex) throws DukeException {
        if (taskIndex >= 0 && taskIndex < tasks.size()) {

            Task t = this.tasks.remove(taskIndex);

            return "Noted. I've removed this task:\n" + "   " + t + "\n"
                    + " Now you have " + tasks.size() + " tasks in the list.";
        } else {
            throw new DukeException("duke.task.Task not found.");
        }
    }

    /**
     * Generates a formatted string containing the list of tasks.
     *
     * @return A formatted string representing the list of tasks.
     */
    public String printList() {
        StringBuilder str;
        if (tasks.isEmpty()) {

            str = new StringBuilder("There are no tasks in the list.");

        } else {

            str = new StringBuilder("Here are the tasks in your list:");
            int taskCount = 1;

            for (Task task : tasks) {
                str.append("\n ").append(taskCount).append(".").append(task);
                taskCount++;
            }
        }

        return str.toString();
    }

    /**
     * Marks a task as done by index and returns a formatted message.
     *
     * @param taskIndex The index of the task to be marked as done.
     * @return A message confirming the task has been marked as done.
     * @throws DukeException If the specified task index is out of bounds.
     */
    public String markTask(int taskIndex) throws DukeException {

        if (taskIndex >= 0 && taskIndex < tasks.size()) {

            Task t = this.tasks.get(taskIndex);
            t.setDone(true);

            return "Nice! I've marked this task as done:\n" + "   " + t;
        } else {
            throw new DukeException("duke.task.Task not found.");
        }
    }

    /**
     * Marks a task as not done by index and returns a formatted message.
     *
     * @param taskIndex The index of the task to be marked as not done.
     * @return A message confirming the task has been marked as not done.
     * @throws DukeException If the specified task index is out of bounds.
     */
    public String unmarkTask(int taskIndex) throws DukeException {
        if (taskIndex >= 0 && taskIndex < tasks.size()) {

            Task t = this.tasks.get(taskIndex);
            t.setDone(false);
            return "OK, I've marked this task as not done yet:\n" + "   " + t;

        } else {
            throw new DukeException("duke.task.Task not found.");
        }
    }

}
