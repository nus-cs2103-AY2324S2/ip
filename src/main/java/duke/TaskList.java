package duke;

import java.util.ArrayList;

/**
 * Represents a task list
 */
public class TaskList {

    private ArrayList<Task> storedTasks;

    TaskList(ArrayList<Task> storedTasks) throws DukeException {
        this.storedTasks = storedTasks;
    }

    TaskList() {
        this.storedTasks = new ArrayList<>();
    }

    public ArrayList<Task> getStoredTasks() {
        return this.storedTasks;
    }

    public boolean checkTaskIdx(int idx) {
        return 0 <= idx && idx < storedTasks.size();
    }

    public int numberOfTask() {
        return storedTasks.size();
    }

    public void addTask(Task t) throws DukeException {
        storedTasks.add(t);
    }

    public void setDone(int idx, boolean done) throws DukeException {
        storedTasks.get(idx).setDone(done);
    }

    /**
     * Pops task at idx
     * @param idx
     * @return
     */
    public Task popTask(int idx) throws DukeException {
        Task t = storedTasks.get(idx);
        storedTasks.remove(idx);
        return t;
    }

    public Task peekTask(int idx) {
        return storedTasks.get(idx);
    }
}
