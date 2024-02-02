package Utility;

import java.util.ArrayList;
import Exceptions.DukeException;
import Task.Task;

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

    public void addTask(Task t) {
        this.list.add(t);
    }

    public void markDone(int i) throws DukeException {
        try {
            Task t = list.get(i - 1);
            t.setDone(true);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Task not found.");
        }
    }

    public void undo(int i) throws DukeException {
        try {
            Task t = list.get(i - 1);
            t.setDone(false);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Task not found.");
        }
    }

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
