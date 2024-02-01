package duke.utility;

import java.util.ArrayList;

import duke.task.Task;
public class TaskList {
    /** ArrayList of Tasks. */
    private ArrayList<Task> taskStore;

    /**
     * Constructs a TaskList object.
     */
    public TaskList() {
        this.taskStore = new ArrayList<Task>();
    }

    /**
     * Constructs a TaskList object with the Tasks inputted.
     *
     * @param loadedTaskStore ArrayList of Tasks to be stored in the TaskList.
     */
    public TaskList(ArrayList<Task> loadedTaskStore) {
        this.taskStore = loadedTaskStore;
    }

    /**
     * Marks Task in the TaskList as done.
     *
     * @param index Index of the Task in the TaskList to be marked as done.
     * @return Task Object that was marked.
     */
    public Task markTask(int index) {
        Task currTask = taskStore.get(index);
        currTask.updateTask(true);
        return currTask;
    }

    /**
     * Unmarks Task in the TaskList as not done.
     *
     * @param index Index of the Task in the TaskList to be marked as not done.
     * @return Task Object that was unmarked.
     */
    public Task unmarkTask(int index) {
        Task currTask = taskStore.get(index);
        currTask.updateTask(false);
        return currTask;
    }

    /**
     * Deletes Task from the TaskList.
     *
     * @param index Index of the Task in the TaskList to be deleted.
     * @return Tasks Object that was removed from the TaskList.
     */
    public Task deleteTask(int index) {
        Task deletedTask = taskStore.remove(index);
        return deletedTask;
    }

    /**
     * Adds Task into the TaskList.
     *
     * @param taskToBeAdded Task Object to be added in the TaskList.
     */
    public void addTask(Task taskToBeAdded) {
        taskStore.add(taskToBeAdded);
    }

    /**
     * Returns the TaskStore.
     *
     * @return ArrayList containing Tasks.
     */
    public ArrayList<Task> getTaskStore() {
        return taskStore;
    }

    /**
     * Returns the size of the TaskList.
     *
     * @return int value of the size of the TaskList.
     */
    public int listSize() {
        return taskStore.size();
    }
}
