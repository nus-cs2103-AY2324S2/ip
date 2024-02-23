package duke.main;
import java.util.ArrayList;

import duke.task.Task;


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
        assert index < this.taskList.size() && index >= 0;
        this.taskList.remove(index);
    }

    /**
     * Marks the task at the specified index as complete
     *
     * @param index  index of task to be marked as done
     */
    public void mark(int index) {
        assert index < this.taskList.size() && index >= 0;
        this.taskList.get(index).setComplete();
    }


    /**
     * Marks the task at the specified index as incomplete
     *
     * @param index  index of task to be marked as not done
     */
    public void unmark(int index) {
        assert index < this.taskList.size() && index >= 0;
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

    /**
     * Finds and returns all tasks with name containing given keyword/key phrase
     * @param keyword  String representation of a word/phrase to search for
     * @return         Tasks in taskList with name containing keyword
     */
    public TaskList find(String keyword) {
        TaskList matchingTasks = new TaskList();
        for (Task t : taskList) {
            if (t.getName().contains(keyword)) {
                matchingTasks.add(t);
            }
        }
        return matchingTasks;
    }
}
