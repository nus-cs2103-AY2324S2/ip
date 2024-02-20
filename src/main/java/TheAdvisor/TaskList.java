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
     */
    public String deleteFromList(int index) {
        if (index < 0) {
            return "Index is out of bounds! We use 1-based indexing here!";
        } else if (checkIndex(index)) {
            Task task = this.taskList.get(index);
            assert task != null : "Task to delete is not there";
            this.taskList.remove(index);
            assert !this.taskList.contains(task) : "Task was not removed from the list";
            return "Noted. I've removed this task:\n" + task.toString() + "\n"
                    + "Now you have " + taskList.size() + " tasks in the list.";
        } else {
            return "We use 1-based indexing for deletion, marking and unmarking! "
                    + "Do try again :)";
        }
    }
    /**
     * Marks a task at the specified index as done.
     *
     * @param index The index of the task to be marked as done.
     * @return A message indicating the successful marking of the task as done,
     *       or a message indicating that the task has already been marked.
     */
    public String markTask(int index) {
        if (index < 0) {
            return "Index is out of bounds! We use 1-based indexing here!";
        } else if (checkIndex(index)) {
            Task task = this.taskList.get(index);
            assert task != null : "Task to mark is present";
            if (!checkMarked(task)) {
                task.markDone();
                assert task.isDone : "Task is not marked as done after marking";
                return "Nice! I've marked this task as done:\n" + task.toString();
            } else {
                return "The task is already marked! You can go ahead and mark/unmark other tasks :).";
            }
        } else {
            return "Index is out of bounds! We use 1-based indexing here!";
        }
    }
    /**
     * Unmarks a task as done based on the given index and prints a confirmation message.
     *
     * @param index The index of the task to be unmarked.
     * @return The confirmation message.
     */
    public String unmarkTask(int index) {
        if (index < 0) {
            return "Index is out of bounds! We use 1-based indexing here!";
        } else if (checkIndex(index)) {
            Task task = this.taskList.get(index);
            assert task != null : "Task to unmark is not present";
            if (!checkUnmarked(task)) {
                task.unmark();
                assert !task.isDone : "Task is still marked as done after unmarking";
                return "OK, I've marked this task as not done yet:\n" + task.toString();
            } else {
                return "The task is already unmarked! You can go ahead and mark/unmark other tasks :).";
            }
        } else {
            return "Index is out of bounds! We use 1-based indexing here!";
        }
    }
    /**
     * Checks if a task is already marked and throws an exception if it is.
     *
     * @param task The task to be checked.
     */
    public boolean checkMarked(Task task) {
        return task.isDone;
    }
    /**
     * Checks if a task is already unmarked and throws an exception if it is.
     *
     * @param task The task to be checked.
     */
    public boolean checkUnmarked(Task task) {
        return !task.isDone;
    }
    /**
     * Checks if the provided index is within the bounds of the task list.
     *
     * @param index The index to be checked.
     * @return True if the index is within bounds and the task list is not empty, false otherwise.
     */
    public boolean checkIndex(int index) {
        assert index > 0 : "Index must be 1-based";
        int size = this.taskList.size();
        return index < size;
    }
    /**
     * Finds tasks in the list that match the given keyword.
     *
     * @param keyword The keyword to search for.
     * @return A string representation of matching tasks, even if only matching partially.
     */
    public String findItem(String keyword) {
        List<Task> matchingTasks = taskList.stream()
                .filter(task -> task.getDescription().contains(keyword))
                .collect(Collectors.toList());
        if (matchingTasks.isEmpty()) {
            return "Sorry! There are no tasks that match your search criteria.";
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
