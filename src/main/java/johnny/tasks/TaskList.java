package johnny.tasks;

import java.util.ArrayList;
import java.util.List;

import johnny.exceptions.JohnnyException;

/**
 * Represents list of Tasks.
 */
public class TaskList {

    /** List of tasks */
    private List<Task> tasks;

    /**
     * Constructor for TaskList using a lists of Tasks.
     *
     * @param tasks List of Tasks.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Constructor for empty TaskList.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Gets a specific Task using the index in the TaskList.
     *
     * @param index Index of Task to be retrieved.
     * @return Task at the given index.
     * @throws JohnnyException If no Task exists at given index.
     */
    public Task get(int index) throws JohnnyException {
        try {
            return tasks.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new JohnnyException("This task does not exist bro.");
        }
    }

    /**
     * Marks a specific Task using the index in the TaskList.
     *
     * @param index Index of Task to be marked.
     * @return Task at the given index.
     * @throws JohnnyException If no Task exists at given index.
     */
    public Task mark(int index) throws JohnnyException {
        Task task = get(index);
        task.mark();
        return task;
    }

    /**
     * Unmarks a specific Task using the index in the TaskList.
     *
     * @param index Index of Task to be unmarked.
     * @return Task at the given index.
     * @throws JohnnyException If no Task exists at given index.
     */
    public Task unmark(int index) throws JohnnyException {
        Task task = get(index);
        task.unmark();
        return task;
    }

    /**
     * Deletes a specific Task using the index in the TaskList.
     *
     * @param index Index of Task to be deleted.
     * @return Task at the given index.
     * @throws JohnnyException If no Task exists at given index.
     */
    public Task delete(int index) throws JohnnyException {
        try {
            return tasks.remove(index);
        } catch (IndexOutOfBoundsException e) {
            throw new JohnnyException("This task does not exist bro.");
        }
    }

    /**
     * Gets size of TaskList.
     *
     * @return Length of TaskList.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Adds Task to TaskList.
     *
     * @param task Task to be added to TaskList.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Find all Tasks in TaskList whose names contains specified keyword.
     *
     * @param keyword String to be matched to Task name.
     * @return TaskList of all Tasks that contains keyword.
     */
    public TaskList find(String keyword) {
        TaskList foundTasks = new TaskList();
        for (Task task: tasks) {
            if (task.contains(keyword)) {
                foundTasks.addTask(task);
            }
        }
        return foundTasks;
    }

    /**
     * Check if 2 TaskLists are equal by checking all Tasks in TaskList.
     *
     * @param o Object to be compared with.
     * @return True if the TaskLists are equal else false.
     */
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (o instanceof TaskList) {
            TaskList t = (TaskList) o;
            if (this.size() != t.size()) {
                return false;
            }
            for (int i = 0; i < this.size(); i++) {
                if (!this.tasks.get(i).equals(t.tasks.get(i))) {
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }

}
