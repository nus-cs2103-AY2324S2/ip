package james.tasklist;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import james.exception.DukeException;
import james.tasks.Task;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;
    private Integer size;

    /**
     * Constructor for a task list.
     *
     * @param tasks List of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
        this.size = tasks.size();
    }

    /**
     * Adds a task to the task list.
     *
     * @param task Task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
        this.size += 1;
    }

    /**
     * Retrieves a task from the task list.
     *
     * @param index Index of the task to retrieve.
     * @return Task at the specified index.
     * @throws DukeException If the index is invalid.
     */
    public Task getTask(int index) throws DukeException {
        if (index < 0 || index >= tasks.size()) {
            throw new DukeException("The task number you are trying to access does not exist.");
        }
        return tasks.get(index);
    }

    /**
     * Deletes a task from the task list.
     *
     * @param index Index of the task to delete.
     * @return Task that was deleted.
     * @throws DukeException If the index is invalid.
     */
    public Task deleteTask(int index) throws DukeException {
        if (index < 0 || index >= tasks.size()) {
            throw new DukeException("The task number you are trying to delete does not exist.");
        }
        size -= 1;
        return tasks.remove(index);
    }

    /**
     * Retrieves the list of tasks.
     *
     * @return List of tasks.
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Retrieves the size of the task list.
     *
     * @return Size of the task list.
     */
    public int getSize() {
        return this.size;
    }

    /**
     * Sorts the tasks in the task list.
     */
    public void sortTasks() {
        Collections.sort(tasks, new Comparator<Task>() {
            @Override
            public int compare(Task task1, Task task2) {
                LocalDateTime time1 = task1.getDateTime();
                LocalDateTime time2 = task2.getDateTime();
                if (time1 == null && time2 == null) {
                    return 0;
                } else if (time1 == null) {
                    return 1;
                } else if (time2 == null) {
                    return -1;
                } else {
                    return time1.compareTo(time2);
                }
            }
        });
    }
}
