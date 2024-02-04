package arona.task;

import java.util.ArrayList;

/**
 * The TaskList class stores the tasks in a list.
 *
 * @author Maximilliano Utomo
 */
public class TaskList {
    /**
     * The tasks stored in an ArrayList.
     */
    private ArrayList<Task> tasks;

    /**
     * A public constructor for the TaskList class.
     */
    public TaskList() {
        tasks = new ArrayList<>(100);
    }

    /**
     * Copies the task list given from the Storage class.
     * @param tasks - the list of tasks
     */
    public void readTasksFromStorage(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Gets the id-th task from the list.
     * @param id - the index
     * @return the Task on the id-th entry of the list
     */
    public Task getTask(int id) {
        return tasks.get(id);
    }

    /**
     * Gets the size of the task list.
     * @return the number of tasks in the list
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Checks whether the list is empty or not.
     * @return True if the list is empty, False otherwise
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Checks whether the given index is available in the task list.
     * @param id - the index to be checked
     * @return True if the index is inside bounds, False otherwise
     */
    public boolean checkIndexValidity(int id) {
        id = id - 1;
        return 0 <= id && id < tasks.size();
    }

    /**
     * Adds a task to the back of the list.
     * @param task - the Task that is added to the list
     */
    public void addElements(Task task) {
        tasks.add(task);
    }

    /**
     * Removes the task of a given index from the list.
     * @param id - the index of the task to be deleted
     */
    public void deleteElements(int id) {
        tasks.remove(id);
    }

    /**
     * Marks the task of given index from the list as done.
     * @param id - the index of the task to be marked
     */
    public void markIndexAsDone(int id) {
        Task task = tasks.get(id);
        task.setDone();
    }

    /**
     * Marks the task of given index from the list as not done.
     * @param id - the index of the task to be unmarked
     */
    public void markIndexAsUndone(int id) {
        Task task = tasks.get(id);
        task.setNotDone();
    }

    @Override
    public String toString() {
        String message = "Sensei! Pick a task. I'll back you up!";
        if (isEmpty()) {
            message = "Sensei! There are no tasks. Take care of your health too. It's gotta take priority!";
        }

        for (int i = 0; i < tasks.size(); i++) {
            message += ("\n" + (i + 1) + ". " + tasks.get(i));
        }

        return message;
    }
}
