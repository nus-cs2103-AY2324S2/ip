package duke.main;
import duke.task.*;

import java.util.ArrayList;

/**
 * Represents the list of tasks generated from user inputs
 */
public class TaskList {
    private final ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Appends new task to the end of the list
     *
     * @param t  New task to be added
     */
    public void add(Task t) {
        this.taskList.add(t);
    }

    /**
     * Removes task at specified index from the list
     *
     * @param index  index of task to be removed
     */
    public void remove(int index) {
        this.taskList.remove(index);
    }

    /**
     * Marks the task at the specified index as complete
     *
     * @param index  index of task to be marked as done
     */
    public void mark(int index) {
        this.taskList.get(index).setComplete();
    }


    /**
     * Marks the task at the specified index as incomplete
     *
     * @param index  index of task to be marked as not done
     */
    public void unmark(int index) {
        this.taskList.get(index).setInComplete();
    }

    /**
     * Retrieves task at specified index
     *
     * @param index  index of task to be returned
     * @return       the task at the specified position in the list
     */
    public Task get(int index) {
        return this.taskList.get(index);
    }

    /**
     * Returns the current size of the task list
     *
     * @return  integer representing size of task list
     */

    public int getSize() {
        return this.taskList.size();
    }
}
