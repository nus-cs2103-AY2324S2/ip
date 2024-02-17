package duke.tasks;

import duke.exceptions.InvalidIndexException;
import duke.exceptions.TaskNotFoundException;

import java.util.ArrayList;
import java.util.List;

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
        System.out.println("     Got it. I've added this task:");
        System.out.println("         " + task.toString());
        getCurTotalTasks();
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
    public void displayTasks() throws InvalidIndexException {
        if (isEmpty()) {
            System.out.println("     No pending duke.tasks, congrats!");
        } else {
            System.out.println("     Here are the tasks in your list:");
            for (int i = 0; i < size(); i++) {
                System.out.println("     " + (i + 1) + ". " + getTask(i));
            }
            getCurTotalTasks();
        }
    }

    /**
     * Deletes a task from the task list by index.
     *
     * @param index the index of the task to delete.
     * @throws InvalidIndexException if the index is invalid (out of bounds).
     */
    public void deleteTask(int index) throws InvalidIndexException {
        try {
            Task task = this.get(index);
            System.out.println("     OK! I've removed this task:");
            System.out.println("         " + task.toString());
            this.remove(index);
            getCurTotalTasks();
        } catch (IndexOutOfBoundsException e) {
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
    public void getCurTotalTasks() {
        int numTask = this.size();
        System.out.println("     Total number of tasks: " + numTask);
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
    public void findTasks(String searchTerm) throws TaskNotFoundException, InvalidIndexException {
        TaskList foundTasks = new TaskList();
        for (Task task : this) {
            if (task.getDescription().contains(searchTerm)) {
                foundTasks.add(task);
            }
        }
        if (searchTerm.isEmpty() || foundTasks.isEmpty()) {
            throw new TaskNotFoundException();
        } else {
            System.out.println("     Here are the matching tasks in your list:");
            for (int i = 0; i < foundTasks.size(); i++) {
                System.out.println("     " + (i + 1) + ". " + foundTasks.getTask(i));
            }
        }
    }

}
