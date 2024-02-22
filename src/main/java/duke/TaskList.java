package duke;

import java.io.Serializable;
import java.util.ArrayList;

import duke.tasks.Task;

/**
 * Represent a TaskList class to record tasks.
 */
public class TaskList implements Serializable {
    protected ArrayList<Task> taskList;

    /**
     * Constructor for the TaskList class.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Constructor for the TaskList class.
     *
     * @param taskList An ArrayList containing tasks.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Return the size of the TaskList.
     *
     * @return int The size of the TaskList.
     */
    public int size() {
        return this.taskList.size();
    }

    /**
     * Get the task from the TaskList.
     *
     * @param index Index of the wanted task.
     * @return Task The wanted task from the TaskList.
     */
    public Task getTask(int index) {
        return this.taskList.get(index);
    }

    /**
     * Add task to TaskList.
     *
     * @param task Task to be added to TaskList.
     */
    public void addTask(Task task) {
        this.taskList.add(task);
    }

    /**
     * Delete task from TaskList.
     *
     * @param index Index of the task tobe deleted.
     */
    public void deleteTask(int index) {
        this.taskList.remove(index);
    }

    /**
     * Set task as done in the TaskList.
     *
     * @param index Index of task to be set as done.
     */
    public void setAsDone(int index) {
        this.taskList.get(index).setAsDone();
    }

    /**
     * Set task as not done in the TaskList.
     *
     * @param index Index of task to be set as not done.
     */
    public void setAsNotDone(int index) {
        this.taskList.get(index).setAsNotDone();
    }

}
