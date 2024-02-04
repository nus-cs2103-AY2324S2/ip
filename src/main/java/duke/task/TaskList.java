package duke.task;

import java.util.LinkedList;
import duke.ui.Ui;

/**
 * Represents a taskList to store tasks.
 */
public class TaskList {
    private static LinkedList<Task> tasks = null;
    private static TaskList instance = null;
    private Ui ui = null;

    /**
     * Returns number of tasks stored in taskList.
     */
    public int getNumOfTasks() {
        return tasks.size();
    }

    public static TaskList getInstance() {
        if (instance == null) {
            instance = new TaskList();
        }
        return instance;
    }

    /**
     * Initialises taskList.
     */
    public void initTaskList() {
        tasks = new LinkedList<>();
        ui = Ui.getInstance();
    }

    /**
     * Adds a task to the taskList.
     *
     * @param task Task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Marks specified task as done/not done.
     *
     * @param index Index of task to be marked.
     * @param isDone New status of the task to be marked.
     * @return The string describing marked/unmarked task.
     * @throws TaskIndexOutOfBoundsException If index specified is out of bounds.
     */
    public String setTaskDoneWithIndex(int index, boolean isDone)
            throws TaskIndexOutOfBoundsException {
        try {
            tasks.get(index - 1).setIsDone(isDone);
            return tasks.get(index - 1).getUpdateIsDoneMessage();
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            throw new TaskIndexOutOfBoundsException(index);
        }
    }

    /**
     * Deletes specified task from the taskList.
     *
     * @param index Index of the task to be deleted.
     * @return Task deleted.
     * @throws TaskIndexOutOfBoundsException If index specified is out of bounds.
     */
    public Task deleteTask(int index) throws TaskIndexOutOfBoundsException {
        try {
            return tasks.remove(index - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new TaskIndexOutOfBoundsException(index);
        }
    }

    public Task getTask(int i) {
        return tasks.get(i - 1);
    }
}
