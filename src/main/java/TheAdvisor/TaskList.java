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
        return "Very well, your task has been added:\n"
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
            return "YOU FOOL! We use 1-based indexing here.";
        } else if (checkIndex(index)) {
            Task task = this.taskList.get(index);
            assert task != null : "Task to delete is not there";
            this.taskList.remove(index);
            assert !this.taskList.contains(task) : "Task was not removed from the list";
            return "Very well. I've removed this task with my supreme visual prowess:\n" + task.toString() + "\n"
                    + "Now you have " + taskList.size() + " tasks in the list.";
        } else {
            return "Hey you, 1-based indexing for deletion, marking and unmarking in here. "
                    + "Stop fooling around or I'll send you off to another dimension";
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
            return "YOU FOOL! We use 1-based indexing here.";
        } else if (checkIndex(index)) {
            Task task = this.taskList.get(index);
            assert task != null : "Task to mark is present";
            if (!checkMarked(task)) {
                task.markDone();
                assert task.isDone : "Task is not marked as done after marking";
                return "Very well. I've marked this task as done with my supreme visual prowess:\n" + task.toString();
            } else {
                return "Are you trying to fool me. The task is already marked! Fool me once more and "
                        + "I'll make sure to send nine-tails on you";
            }
        } else {
            return "Hey you, 1-based indexing for deletion, marking and unmarking in here. "
                    + "Stop fooling around or I'll send you off to another dimension";
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
            return "YOU FOOL! We use 1-based indexing here.";
        } else if (checkIndex(index)) {
            Task task = this.taskList.get(index);
            assert task != null : "Task to unmark is not present";
            if (!checkUnmarked(task)) {
                task.unmark();
                assert !task.isDone : "Task is still marked as done after unmarking";
                return "Very well. I've unmarked this task with my supreme visual prowess:\n" + task.toString();
            } else {
                return "Are you trying to fool me. The task is already unmarked! Fool me once more and "
                        + "I'll make sure to send nine-tails on you";
            }
        } else {
            return "Hey you, 1-based indexing for deletion, marking and unmarking in here. "
                    + "Stop fooling around or I'll send you off to another dimension";
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
            return "Unfortunate. There are nothing matching who you are finding despite me using my "
                    + "all-seeing eyes to help you seek.";
        } else {
            StringBuilder toReturn = new StringBuilder("My eyes have shown it to me, those that you seek are here:\n");
            for (int j = 0; j < matchingTasks.size(); j++) {
                Task task = matchingTasks.get(j);
                toReturn.append(j + 1).append(". ").append(task.toString()).append("\n");
            }
            return toReturn.toString();
        }
    }
    /**
     * Checks whether the specified task is present in the task list.
     *
     * @param task The task to be checked for presence in the task list.
     * @return {@code true} if the task is present in the task list, {@code false} otherwise.
     */
    public boolean contains(Task task) {
        return taskList.contains(task);
    }

}
