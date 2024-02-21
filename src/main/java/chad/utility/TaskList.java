package chad.utility;

import java.util.ArrayList;

import chad.exceptions.ChadException;
import chad.task.Task;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    private ArrayList<Task> list;

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
     * @throws ChadException if task chosen is not found in task list.
     */
    public void markDone(int i) throws ChadException {
        try {
            Task t = list.get(i - 1);
            t.setDone(true);
        } catch (IndexOutOfBoundsException e) {
            throw new ChadException("Task not found.");
        }
    }

    /**
     * Marks a task as undone.
     *
     * @param i the index of the task to be marked
     * @throws ChadException if task chosen is not found in task list.
     */
    public void undo(int i) throws ChadException {
        try {
            Task t = list.get(i - 1);
            t.setDone(false);
        } catch (IndexOutOfBoundsException e) {
            throw new ChadException("Task not found.");
        }
    }

    /**
     * Deletes a task in this task list.
     *
     * @param i the index of the task to be deleted
     * @throws ChadException if task chosen is not found in task list.
     */
    public void deleteTask(int i) throws ChadException {
        try {
            list.remove(i - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new ChadException("Task not found.");
        }
    }

    /**
     * Search the task list for a matching keyword.
     *
     * @param keyword the word to search for.
     * @return a list of tasks with the matching keyword
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

    public void clear() {
        this.list = new ArrayList<Task>();
    }
}
