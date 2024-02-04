/**
 * Stores and provides operations for the list of tasks in the Bond task
 * management program.
 * 
 * @author Benny Loh
 * @version 0.1
 */
package bond.task;

import java.util.ArrayList;
import java.util.ListIterator;

public class TaskList {

    private ArrayList<Task> tasks;

    /**
     * Constructor for the TaskList class.
     * 
     * @param taskList The list of tasks to be stored in the TaskList.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.tasks = taskList == null ? new ArrayList<>() : taskList;
    }

    public int numberOfTasks() {
        return this.tasks.size();
    }

    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    public void addTask(Task newTask) {
        this.tasks.add(newTask);
    }

    public void deleteTask(int index) {
        this.tasks.remove(index);
    }

    public boolean noTasks() {
        return this.tasks.isEmpty();
    }

    /**
     * Returns a list iterator for the tasks in the task list.
     */
    public ListIterator<Task> getTasks() {
        return this.tasks.listIterator();
    }

}
