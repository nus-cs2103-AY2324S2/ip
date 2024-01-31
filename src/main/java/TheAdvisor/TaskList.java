package theadvisor;

import java.util.ArrayList;
import java.io.Serializable;

/**
 * The TaskList class represents a list of tasks and provides methods for managing tasks.
 * It includes functionality to add, delete, mark, unmark and find tasks in the list.
 */
public class TaskList implements Serializable {
    private ArrayList<Task> taskList;

    /**
     * Constructs a new TaskList with an empty list of tasks.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Gets the list of tasks.
     *
     * @return The list of tasks.
     */
    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    /**
     * Adds a task to the list and prints a confirmation message.
     *
     * @param task The task to be added.
     */
    public void addToList(Task task) {
        this.taskList.add(task);
        System.out.println("     Got it. I've added this task:\n" +
                "       " + task.toString() + "\n" +
                "     Now you have " + taskList.size() +
                " tasks in the list.");
    }

    /**
     * Deletes a task from the list based on the given index and prints a confirmation message.
     *
     * @param index The index of the task to be deleted.
     * @throws TheAdvisorException If the index is out of bounds or the list is empty.
     */
    public void deleteFromList(int index) throws TheAdvisorException {
        try {
            checkIndex(index);
            Task task = this.taskList.get(index);
            this.taskList.remove(index);
            System.out.println("     Noted. I've removed this task:\n" + "       " +
                    task.toString() + "\n" + "     Now you have " + taskList.size() + " tasks in the list.");
        } catch (IndexOutOfBoundsException e) {
            throw new TheAdvisorException("We use 1-based indexing for deletion, marking and unmarking! Do try again :)");
        }
    }

    /**
     * Marks a task as done based on the given index and prints a confirmation message.
     *
     * @param index The index of the task to be marked.
     * @throws TheAdvisorException If the index is out of bounds or the task is already marked.
     */
    public void markTask(int index) throws TheAdvisorException {
        try {
            checkIndex(index);
            Task task = this.taskList.get(index);
            checkMarked(task);
            task.markDone();
            System.out.println("     Nice! I've marked this task as done:\n" + "       " +
                    task.toString());
        } catch (IndexOutOfBoundsException e) {
            throw new TheAdvisorException("We use 1-based indexing for deletion, marking and unmarking! Do try again :)");
        }
    }

    /**
     * Unmarks a task as done based on the given index and prints a confirmation message.
     *
     * @param index The index of the task to be unmarked.
     * @throws TheAdvisorException If the index is out of bounds or the task is already unmarked.
     */
    public void unmarkTask(int index) throws TheAdvisorException {
        try {
            checkIndex(index);
            Task task = this.taskList.get(index);
            checkUnmarked(task);
            task.unmark();
            System.out.println("          OK, I've marked this task as not done yet:\n" + "       " +
                    task.toString());
        } catch (IndexOutOfBoundsException e) {
            throw new TheAdvisorException("We use 1-based indexing for deletion, marking and unmarking! Do try again :)");
        }
    }

    /**
     * Gets the number of tasks in the list.
     *
     * @return The number of tasks in the list.
     */
    public int getSize() {
        return this.taskList.size();
    }

    /**
     * Gets a string representation of the task at the specified index.
     *
     * @param index The index of the task.
     * @return A string representing the task.
     */
    public String getTask(int index) {
        return taskList.get(index).toString();
    }

    /**
     * Checks if a task is already marked and throws an exception if it is.
     *
     * @param task The task to be checked.
     * @throws TheAdvisorException If the task is already marked.
     */
    public void checkMarked(Task task) throws TheAdvisorException {
        if (task.isDone) {
            throw new TheAdvisorException("The task is already marked! Carry on.");
        }
    }

    /**
     * Checks if a task is already unmarked and throws an exception if it is.
     *
     * @param task The task to be checked.
     * @throws TheAdvisorException If the task is already unmarked.
     */
    public void checkUnmarked(Task task) throws TheAdvisorException {
        if (!task.isDone) {
            throw new TheAdvisorException("The task is already unmarked! Carry on.");
        }
    }

    /**
     * Checks if the given index is valid for the list.
     *
     * @param index The index to be checked.
     * @throws TheAdvisorException If the index is out of bounds or the list is empty.
     */
    public void checkIndex(int index) throws TheAdvisorException {
        int size = this.taskList.size();
        if (index < 0) {
            throw new TheAdvisorException("We use 1-indexing for marking. Please try again.");
        } else if (index > size) {
            throw new TheAdvisorException("Out of bounds. We use 1-indexing for marking. Please try again.");
        } else if (size == 0) {
            throw new TheAdvisorException("The list is empty! Start adding in things :)");
        }
    }

    /**
     * Comb through the TaskList to see if there are any tasks in the list that matches the keyword.
     * Print out the matching task(s) if any. Else inform the user that there is no tasks with such keyword.
     *
     * @param keyword The keyword the user is looking for from the list.
     */
    public void findItem(String keyword) throws TheAdvisorException {
        ArrayList<Integer> indexes = new ArrayList<>();

        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i).getDescription().contains(keyword)) {
                indexes.add(i);
            }
        }

        if (indexes.size() == 0) {
            throw new TheAdvisorException("Sorry! There is no such item that fits your description (,,>  <,,)");
        } else {
            System.out.println("     Here are the matching tasks in your list:");
            for (int j = 0; j < indexes.size(); j++) {
                Task task = taskList.get(indexes.get(j));
                System.out.println("     " + (j + 1) + ". " + task.toString());
            }
        }
    }
}
