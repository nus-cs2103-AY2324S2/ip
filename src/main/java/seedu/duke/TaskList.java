package seedu.duke;

import java.util.ArrayList;

import seedu.duke.task.Task;

/**
 * Represents the <code>class</code> that is able to contain
 * and modify the list of tasks created by the user
 */
public class TaskList {
    private ArrayList<Task> list;

    /**
     * Constructor given list, when there is a pre-saved file.
     * @param list
     */
    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    /**
     * Constructor not given list, in the event where there is
     * no pre-saved file.
     */
    public TaskList() {
        this.list = new ArrayList<Task>();
    }

    /**
     * Returns the Task list. Get method.
     * @return list of <code>Task</code>
     */
    public ArrayList<Task> getList() {
        return list;
    }

    /**
     * Returns task based on index. Get method.
     * @param index Represents the task index to get
     * @return Task
     */
    public Task getTask(int index) {
        assert index >= 0 : "Index should be greater than or equal to 0";
        return list.get(index);
    }

    /**
     * Marks <code>Task</code> as done based on index.
     * @param index Represents the correct <code>Task</code> index
     *              to be marked as done.
     */
    public void markTaskDone(int index) {
        assert index >= 0 : "Index should be greater than or equal to 0";
        list.get(index).markAsDone();
    }

    /**
     * Marks <code>Task</code> as undone based on index.
     * @param index Represents the correct <code>Task</code> index
     *              to be marked as undone.
     */
    public void markTaskUndone(int index) {
        assert index >= 0 : "Index should be greater than or equal to 0";
        list.get(index).markAsUndone();
    }

    /**
     * Adds <code>Task</code> to the list of Tasks.
     * @param task Represents the <code>Task</code> object to be
     *             added
     */
    public void addTask(Task task) {
        assert task != null : "Task should not be null";
        list.add(task);
    }

    /**
     * Deletes <code>Task</code> based on the index.
     * @param index Represents the task index to be deleted
     */
    public void deleteTask(int index) {
        assert index >= 0 : "Index should be greater than or equal to 0";
        list.remove(index);
    }

    /**
     * Returns size of Task list.
     * @return size of type <code>int</code>
     */
    public int getSize() {
        return list.size();
    }
}
