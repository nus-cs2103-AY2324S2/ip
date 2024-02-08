package tasklist;

import java.util.ArrayList;

import tasks.Task;

/**
 * <p>
 *  represents a collection of tasks that the user wants saved.
 *  </p>
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * initialises an empty Tasklist
     */
    public TaskList() {

        this.tasks = new ArrayList<Task>();
    }

    /**
     * initialises a Tasklist filled with task from an array
     * @param tasks an arrayList of task to be stored in TaskList
     */
    public TaskList(ArrayList<Task> tasks) {

        this.tasks = tasks;
    }

    public ArrayList<Task> getTaskList() {

        return tasks;
    }
}
