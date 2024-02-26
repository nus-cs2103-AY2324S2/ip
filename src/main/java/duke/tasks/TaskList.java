package duke.tasks;

import duke.exceptions.InvalidIndexException;
import duke.exceptions.TaskNotFoundException;

import java.util.ArrayList;

/**
 * The TaskList class represents a list of tasks.
 * It extends the ArrayList class to store tasks and provides methods for adding, retrieving, displaying, and deleting tasks.
 * <p>
 * The class includes methods to add tasks, retrieve tasks by index, display all tasks, and delete tasks by index.
 * </p>
 * <p>
 * Tasks in the list are indexed starting from 1 for user-friendly display purposes.
 * </p>
 * <p>
 * Note: This class extends ArrayList<Task> to inherit list operations and behavior.
 * </p>
 *
 * @author Justin Leng Chern Harn
 * @version 1.0
 * @see duke.tasks.Task
 * @see duke.exceptions.InvalidIndexException
 */
public class TaskList extends ArrayList<Task> {

    /**
     * Adds a task to the task list.
     *
     * @param task the task to add.
     */
    public void addTask(Task task) {
        add(task);
    }

    /**
     * Retrieves a task from the task list by index.
     *
     * @param index the index of the task to retrieve.
     * @return the task at the specified index.
     * @throws InvalidIndexException if the index is invalid (out of bounds).
     */
    public Task getTask(int index) throws InvalidIndexException {
        try {
            return this.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidIndexException();
        }
    }

    /**
     * Displays all tasks in the task list.
     *
     * @throws InvalidIndexException if there are no tasks in the list.
     */
    public String displayTasks() throws InvalidIndexException {
        StringBuilder result = new StringBuilder();

        if (isEmpty()) {
            result.append("No pending duke.tasks, congrats!");
        } else {
            result.append("So much to do dude,\n");
            for (int i = 0; i < size(); i++) {
                result.append("     ").append((i + 1)).append(". ").append(getTask(i)).append("\n");
            }
            result.append(getCurTotalTasks());
        }
        return result.toString();
    }

    /**
     * Deletes a task from the task list by index.
     *
     * @param index the index of the task to delete.
     * @throws InvalidIndexException if the index is invalid (out of bounds).
     */
    public void deleteTask(int index) throws InvalidIndexException {
        try {
            this.remove(index);
        } catch (IndexOutOfBoundsException e  ) {
            throw new InvalidIndexException();
        }
    }

    /**
     * Prints the total number of tasks currently stored in the task list.
     * <p>
     * This method calculates the total number of tasks in the task list and prints
     * the result to the standard output.
     * </p>
     */
    public String getCurTotalTasks() {
        int numTask = this.size();
        return String.format("Total number of tasks: %d", numTask);
    }

    /**
     * Finds tasks in the task list based on a search term.
     * <p>
     * This method searches for tasks in the task list based on a search term and prints
     * the matching tasks to the standard output.
     * </p>
     *
     * @param searchTerm the search term to match tasks against.
     * @throws TaskNotFoundException if no tasks match the search term.
     * @throws InvalidIndexException if the index is invalid (out of bounds).
     */
    public StringBuilder findTasks(String searchTerm) throws TaskNotFoundException, InvalidIndexException {
        TaskList foundTasks = new TaskList();
        StringBuilder result = new StringBuilder();
        for (Task task : this) {
            if (task.getDescription().contains(searchTerm)) {
                foundTasks.add(task);
            }
        }
        if (searchTerm.isEmpty() || foundTasks.isEmpty()) {
            throw new TaskNotFoundException();
        } else {
            result.append("I found some!\n");
            for (int i = 0; i < foundTasks.size(); i++) {
                result.append("     ").append((i + 1)).append(". ").append(foundTasks.getTask(i)).append("\n");
            }
        }
        return result;
    }

}
