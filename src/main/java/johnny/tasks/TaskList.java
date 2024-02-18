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
     * @param indices Indices of Task to be marked.
     * @return Task at the given index.
     * @throws JohnnyException If no Task exists at given index.
     */
    public List<Task> mark(List<Integer> indices) throws JohnnyException {
        try {
            List<Task> markedTasks = new ArrayList<>();
            for (int i = 0; i < indices.size(); i++) {
                int index = indices.get(i);
                Task task = tasks.get(index);
                task.mark();
                markedTasks.add(task);
            }
            return markedTasks;
        } catch (IndexOutOfBoundsException e) {
            throw new JohnnyException("Some of the tasks do not exist bro.");
        }
    }

    /**
     * Unmarks a specific Task using the index in the TaskList.
     *
     * @param indices Indices of Task to be unmarked.
     * @return Task at the given index.
     * @throws JohnnyException If no Task exists at given index.
     */
    public List<Task> unmark(List<Integer> indices) throws JohnnyException {
        try {
            List<Task> unmarkedTasks = new ArrayList<>();
            for (int i = 0; i < indices.size(); i++) {
                int index = indices.get(i);
                Task task = tasks.get(index);
                task.unmark();
                unmarkedTasks.add(task);
            }
            return unmarkedTasks;
        } catch (IndexOutOfBoundsException e) {
            throw new JohnnyException("Some of the tasks do not exist bro.");
        }

    }

    /**
     * Deletes a specific Task using the index in the TaskList.
     *
     * @param indices Indices of Task to be deleted.
     * @return Task at the given index.
     * @throws JohnnyException If no Task exists at given index.
     */
    public List<Task> delete(List<Integer> indices) throws JohnnyException {
        try {
            List<Task> deletedTasks = new ArrayList<>();
            for (int i = 0; i < indices.size(); i++) {
                int index = indices.get(i);
                Task deletedTask = tasks.remove(index);
                deletedTasks.add(deletedTask);
            }
            return deletedTasks;
        } catch (IndexOutOfBoundsException e) {
            throw new JohnnyException("Some of the tasks do not exist bro.");
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
     * Finds all Tasks in TaskList whose names contains specified keyword.
     *
     * @param keyword String to be matched to Task name.
     * @return All Tasks that contains keyword.
     */
    public List<Task> find(String keyword) {
        List<Task> foundTasks = new ArrayList<>();
        for (Task task: tasks) {
            if (task.contains(keyword)) {
                foundTasks.add(task);
            }
        }
        return foundTasks;
    }

    /**
     * Checks if 2 TaskLists are equal by checking all Tasks in TaskList.
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
