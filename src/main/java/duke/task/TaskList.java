package duke.task;

import java.util.ArrayList;

/**
 * A list of tasks.
 */
public class TaskList {
    private final ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Constructor for task list
     */
    public TaskList() { }

    /**
     * Adds task to the task list.
     * Returns the string representation of the newly added task.
     *
     * @param task New user task.
     * @return String representation of the newly added task.
     */
    public String addTask(Task task) {
        tasks.add(task);
        return task.toString();
    }


    /**
     * Deletes task of the specified index from the task list.
     * Returns the string representation of the newly added task.
     *
     * @param index Index of the task to delete, starting from 1.
     * @return String representation of the deleted task.
     */
    public String deleteTask(int index) {
        return tasks.remove(index - 1).toString();
    }

    /**
     * Marks task of the specified index in the task list as completed.
     * Returns the string representation of the newly added task.
     *
     * @param index Index of the task to mark - starting from 1.
     * @return String representation of the marked task.
     */
    public String markTask(int index) {
        tasks.get(index - 1).setDone();
        return tasks.get(index - 1).toString();
    }

    /**
     * Marks task of the specified index in the task list as not completed.
     * Returns the string representation of the newly added task.
     *
     * @param index Index of the task to unmark - starting from 1.
     * @return String representation of the unmarked task.
     */
    public String unmarkTask(int index) {
        tasks.get(index - 1).setNotDone();
        return tasks.get(index - 1).toString();
    }

    public int getSize() {
        return tasks.size();
    }

    /**
     * Returns a string representation of the task list to be stored in storage.
     *
     * @return String representation of task list to be stored in storage.
     */
    public String storeTasks() {
        StringBuilder savedTask = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            savedTask.append(tasks.get(i).storeData()).append("\n");
        }
        return savedTask.toString();
    }

    @Override
    public String toString() {
        StringBuilder tasksStr = new StringBuilder();
        for (int i = 1; i <= tasks.size(); i++) {
            tasksStr.append("     ").append(i).append(".")
                    .append(tasks.get(i - 1).toString()).append("\n");
        }
        return tasksStr.toString();
    }
}
