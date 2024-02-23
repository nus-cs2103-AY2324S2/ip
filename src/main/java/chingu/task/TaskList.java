package chingu.task;

import java.util.ArrayList;


/**
 * Class that contains a list of tasks
 */
public class TaskList {

    public ArrayList<Task> tasks;

    /**
     * Creates an instance of Tasklist where there is no task in the file
     *
     */
    public  TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Creates an instance of Tasklist which existing tasks from the file
     *
     * @param tasks
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns the specific task with specific index from the tasklist
     *
     * @param i index of the task in the tasklist
     * @return
     */
    public Task getTask(int i) {
        return tasks.get(i);
    }

    /**
     * Update the number of tasks in the list in string form
     *
     * @return String which updates the number of task in the list
     */
    public String getSize() {
        return "Now you have " + tasks.size() + " tasks in the list.";
    }

    /**
     * Returns the number of tasks in the list
     *
     * @return the current number of the tasks in the list
     */
    public int getSizeNumber() {
        return tasks.size();
    }

    /**
     * Adds a new task into the tasklist
     *
     * @param task - new task to be added to the tasklist
     */
    public void add(Task task) {
        this.tasks.add(task);
    }


}
