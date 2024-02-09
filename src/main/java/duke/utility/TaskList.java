package duke.utility;

import java.util.ArrayList;

import duke.exceptions.DukeException;
import duke.task.Task;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    private final ArrayList<Task> list;

    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    public TaskList() {
        list = new ArrayList<>();
    }

    public ArrayList<Task> getList() {
        return this.list;
    }

    /**
     * Adds a task to this task list.
     *
     * @param t the task to be added
     */
    public void addTask(Task t) {
        this.list.add(t);
    }

    /**
     * Marks a task as done.
     *
     * @param i the index of the task to be marked
     * @throws DukeException if task chosen is not found in task list.
     */
    public void markDone(int i) throws DukeException {
        try {
            Task t = list.get(i - 1);
            t.setDone(true);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Task not found.");
        }
    }

    /**
     * Marks a task as undone.
     *
     * @param i the index of the task to be marked
     * @throws DukeException if task chosen is not found in task list.
     */
    public void undo(int i) throws DukeException {
        try {
            Task t = list.get(i - 1);
            t.setDone(false);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Task not found.");
        }
    }

    /**
     * Deletes a task in this task list.
     *
     * @param i the index of the task to be deleted
     * @throws DukeException if task chosen is not found in task list.
     */
    public void deleteTask(int i) throws DukeException {
        try {
            Task t = list.remove(i - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Task not found.");
        }
    }

    /**
     * Search the task list for a matching keyword.
     *
     * @param keyword the word to search for.
     * @return a list of tasks with the matching keyword.
     */
    public ArrayList<Task> searchList(String keyword) {
        ArrayList<Task> matchingList = new ArrayList<>();
        for (Task t : this.list) {
            if (t.getName().contains(keyword)) {
                matchingList.add(t);
            }
        }
        return matchingList;
    }
}
