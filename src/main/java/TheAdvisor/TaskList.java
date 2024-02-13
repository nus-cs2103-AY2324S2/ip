package theadvisor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
     * @return The confirmation message.
     */
    public String addToList(Task task) {
        this.taskList.add(task);
        return "Got it. I've added this task:\n"
                + task.toString() + "\n"
                + "Now you have " + taskList.size()
                + " tasks in the list.";
    }

    /**
     * Deletes a task from the list based on the given index and prints a confirmation message.
     *
     * @param index The index of the task to be deleted.
     * @return The confirmation message.
     * @throws TheAdvisorException If the index is out of bounds or the list is empty.
     */
    public String deleteFromList(int index) throws TheAdvisorException {
        try {
            checkIndex(index);
            Task task = this.taskList.get(index);
            this.taskList.remove(index);
            return "Noted. I've removed this task:\n" + task.toString() + "\n"
                    + "Now you have " + taskList.size() + " tasks in the list.";
        } catch (IndexOutOfBoundsException e) {
            throw new TheAdvisorException("We use 1-based indexing for deletion, marking and unmarking! "
                    + "Do try again :)");
        }
    }

    /**
     * Marks a task as done based on the given index and prints a confirmation message.
     *
     * @param index The index of the task to be marked.
     * @return The confirmation message.
     * @throws TheAdvisorException If the index is out of bounds or the task is already marked.
     */
    public String markTask(int index) throws TheAdvisorException {
        try {
            checkIndex(index);
            Task task = this.taskList.get(index);
            checkMarked(task);
            task.markDone();
            return "Nice! I've marked this task as done:\n" + task.toString();
        } catch (IndexOutOfBoundsException e) {
            throw new TheAdvisorException("We use 1-based indexing for deletion, marking, and unmarking! "
                    + "Please try again :)");
        }
    }

    /**
     * Unmarks a task as done based on the given index and prints a confirmation message.
     *
     * @param index The index of the task to be unmarked.
     * @return The confirmation message.
     * @throws TheAdvisorException If the index is out of bounds or the task is already unmarked.
     */
    public String unmarkTask(int index) throws TheAdvisorException {
        try {
            checkIndex(index);
            Task task = this.taskList.get(index);
            checkUnmarked(task);
            task.unmark();
            return "OK, I've marked this task as not done yet:\n" + task.toString();
        } catch (IndexOutOfBoundsException e) {
            throw new TheAdvisorException("We use 1-based indexing for deletion, marking, and unmarking! "
                    + "Please try again :)");
        }
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
            throw new TheAdvisorException("The list is empty! Start adding things :)");
        }
    }

    /**
     * Finds tasks in the list that match the given keyword.
     *
     * @param keyword The keyword to search for.
     * @return A string representation of matching tasks.
     * @throws TheAdvisorException If no matching tasks are found.
     */
    public String findItem(String keyword) throws TheAdvisorException {
        List<Task> matchingTasks = taskList.stream()
                .filter(task -> task.getDescription().contains(keyword))
                .collect(Collectors.toList());

        if (matchingTasks.isEmpty()) {
            throw new TheAdvisorException("Sorry! There are no tasks that match your search criteria.");
        } else {
            StringBuilder toReturn = new StringBuilder("Here are the matching tasks in your list:\n");
            for (int j = 0; j < matchingTasks.size(); j++) {
                Task task = matchingTasks.get(j);
                toReturn.append(j + 1).append(". ").append(task.toString()).append("\n");
            }
            return toReturn.toString();
        }
    }

}
