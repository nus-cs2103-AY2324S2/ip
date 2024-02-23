package duke.task;

import java.util.ArrayList;

import duke.commons.exceptions.DukeException;

import java.io.Serializable;

/**
 * Represents a list of tasks; encapsulates operations such as adding, deleting,
 * and marking tasks as done or undone.
 * It also provides functionality to print the list of tasks and query the
 * number of tasks.
 */
public class TaskList implements Serializable {

    public static final String INDENT = "     ";

    public static final String LINE = "____________________________________________________________";

    private ArrayList<Task> tasks;

    /**
     * Constructs a new, empty task list.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds a task to the list.
     * 
     * @param task The task to be added.
     * @return String The string representation of the added task.
     */
    public String addTask(Task task) throws DukeException {
        if (checkDuplicatedTask(task)) {
            throw new DukeException("Task is duplicated.\n" + task.toString());
        }
        tasks.add(task);
        return task.toString();
    }

    public boolean checkDuplicatedTask(Task task) {
        boolean isDuplicated = false;
        for (Task otherTask : tasks) {
            if (task.compareTo(otherTask) == 0) {
                isDuplicated = true;
                break;
            }
        }
        return isDuplicated;
    }

    /**
     * Marks a specified task as done.
     * 
     * @param idx The index of the task to mark as done.
     * @return The string representation of the task marked as done.
     * @throws DukeException If the specified index is invalid.
     */
    public String markTaskDone(int idx) throws DukeException {
        assert idx >= 0 : "Negative indices to be handled in parser";
        if (idx >= 0 && idx < tasks.size()) {
            tasks.get(idx).markDone();
            return tasks.get(idx).toString();
        } else {
            throw new DukeException("Invalid task index: " + idx);
        }
    }

    /**
     * Marks a specified task as undone.
     * 
     * @param idx The index of the task to mark as undone.
     * @return The string representation of the task marked as undone.
     * @throws DukeException If the specified index is invalid.
     */
    public String markTaskUndone(int idx) throws DukeException {
        assert idx >= 0 : "Negative indices to be handled in parser";
        if (idx >= 0 && idx < tasks.size()) {
            tasks.get(idx).markUndone();
            return tasks.get(idx).toString();
        } else {
            throw new DukeException("Invalid task index: " + idx);
        }
    }

    /**
     * Deletes a specified task from the list.
     * 
     * @param idx The index of the task to be deleted.
     * @return The string representation of the deleted task.
     * @throws DukeException If the specified index is invalid.
     */
    public String deleteTask(int idx) throws DukeException {
        assert idx >= 0 : "Negative indices to be handled in parser";
        if (idx >= 0 && idx < tasks.size()) {
            String taskDescription = tasks.get(idx).toString();
            tasks.remove(idx);
            return taskDescription;
        } else {
            throw new DukeException("Invalid task index: " + idx);
        }
    }

    public ArrayList<Integer> findTasksByKeywordsMatching(String[] keywords) throws DukeException {
        ArrayList<Integer> matchingTaskIndices = new ArrayList<>();
        for (int i = 0; i < getNumberTasks(); i++) {
            for (String keyword : keywords) {
                if (tasks.get(i).containsKeyword(keyword)) {
                    matchingTaskIndices.add(i);
                    break;
                }
            }
        }
        return matchingTaskIndices;
    }

    public int getNumberTasks() {
        return tasks.size();
    }

    public ArrayList<String> getTaskRepresentationsByIndices(ArrayList<Integer> indices) {
        ArrayList<String> taskRepresentations = new ArrayList<>();
        for (int i : indices) {
            taskRepresentations.add(tasks.get(i).getRepresentation());
        }
        return taskRepresentations;
    }

    public String getFormattedTasks() {
        StringBuilder sb = new StringBuilder();
        if (tasks.size() == 0) {
            sb.append("no tasks");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                sb.append((i + 1) + ". " + tasks.get(i) + "\n");
            }
        }
        return sb.toString();
    }
}
