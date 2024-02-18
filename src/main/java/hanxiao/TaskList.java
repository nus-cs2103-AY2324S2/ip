package hanxiao;

import java.util.ArrayList;

import hanxiao.exception.HanxiaoException;
import hanxiao.exception.DuplicateTaskException;
import hanxiao.task.Task;

/**
 * Class for a task list were we store all the tasks
 */
public class TaskList {
    protected ArrayList<Task> taskArrayList;

    /**
     * Constructor of a task list.
     *
     * @param taskArrayList an ArrayList, could be empty or with tasks.
     */
    public TaskList(ArrayList<Task> taskArrayList) {
        this.taskArrayList = taskArrayList;
    }

    /**
     * Return the task list.
     *
     * @return ArrayList of tasks.
     */
    public ArrayList<Task> getTaskList() {
        return taskArrayList;
    }

    /**
     * Add task to task list.
     *
     * @param task The task we want to add.
     */
    public void addTask(Task task) throws DuplicateTaskException {
        if (taskArrayList.contains(task)) {
            throw new DuplicateTaskException(task);
        }
        taskArrayList.add(task);
    }

    /**
     * Remove the task from task list and return it.
     *
     * @param index Index start from 1.
     * @return The removed task.
     */
    public Task removeTask(int index) {
        assert index < taskArrayList.size() : "index should not exceed the list length";
        return taskArrayList.remove(index);
    }

    /**
     * Return the length of a task list.
     *
     * @return Length of the task list.
     */
    public int getListLength() {
        return this.taskArrayList.size();
    }

    /**
     * Return the task.
     *
     * @param index Index of a task, star from 0.
     * @return The task requested.
     */
    public Task getTask(int index) {
        assert index < taskArrayList.size() : "index should not exceed the list length";
        return this.taskArrayList.get(index);
    }

    /**
     * Reset task list.
     *
     * @param taskList
     */
    public void resetTaskList(ArrayList<Task> taskList) {
        this.taskArrayList = taskList;
    }

    /**
     * Sort Task list.
     */
    public void sortTaskList() {
        taskArrayList.sort((x, y) -> x.compareTo(y));
    }

    /**
     * Update task.
     * @param index index.
     * @param updateField updated field.
     * @param updateValue updated value.
     * @throws HanxiaoException might have wrong usage.
     */
    public void updateTask(int index, String updateField, String updateValue) throws HanxiaoException {
        taskArrayList.get(index).updateTask(updateField, updateValue);
    }

}
