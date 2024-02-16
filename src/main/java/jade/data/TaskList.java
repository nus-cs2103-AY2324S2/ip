package jade.data;

import java.util.ArrayList;
import java.util.List;

/**
 * The <code>TaskList</code> object represents a list consists of user tasks.
 */
public class TaskList<T extends Task> {
    private final List<T> taskList;

    /**
     * A Class constructor initializing an empty list to save user tasks.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Another Class constructor specifying the list that saves all user tasks.
     */
    public TaskList(List<T> taskList) {
        this.taskList = taskList;
    }

    /**
     * Returns the number of tasks in the list.
     */
    public int size() {
        return taskList.size();
    }

    /**
     * Adds the task to the list.
     */
    public void add(T task) {
        taskList.add(task);
    }

    /**
     * Removes the task at the specified index.
     */
    public void remove(int index) {
        taskList.remove(index);
    }

    /**
     * Marks the task as done at the specified index.
     */
    public void mark(int index) {
        taskList.get(index).mark();
    }

    /**
     * Marks the task as not done yet at the specified index.
     */
    public void unmark(int index) {
        taskList.get(index).unmark();
    }

    /**
     * Checks whether the list has no tasks.
     *
     * @return 0 if there is no tasks in the list.
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Returns the Task at the specified index.
     */
    public T get(int index) {
        return taskList.get(index);
    }

    /**
     * Returns the formatted string of all tasks in the list.
     * Each task starts at a new line.
     */
    public String listFormatter() {
        StringBuilder sb = new StringBuilder();
        for (Task task : taskList) {
            sb.append(task.taskFormatter());
        }
        return sb.toString();
    }
}
