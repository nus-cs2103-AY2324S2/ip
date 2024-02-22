package Martin;

import java.util.ArrayList;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructs a TaskList object with the given list of tasks.
     *
     * @param tasks The list of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Retrieves the list of tasks.
     *
     * @return The list of tasks.
     */
    public ArrayList<Task> getTodoList() {
        return this.tasks;
    }

    /**
     * Adds a task to the list.
     *
     * @param task The task to be added.
     * @return A string representation of the added task.
     */
    public String add(Task task) {
        tasks.add(task);
        return "task added: " + task.toString() + "\n";
    }

    /**
     * Removes a task from the list at the specified index.
     *
     * @param index The index of the task to be removed.
     * @return The removed task.
     */
    public Task remove(int index) {
        return tasks.remove(index);
    }

    /**
     * Marks a task at the specified index as done.
     *
     * @param index The index of the task to be marked as done.
     * @return A string representation of the marked task.
     */
    public String markAsDone(int index) {
        Task task = tasks.get(index);
        task.markAsDone();
        return task.toString();
    }

    /**
     * Marks a task at the specified index as not done.
     *
     * @param index The index of the task to be marked as not done.
     * @return A string representation of the unmarked task.
     */
    public String unmarkAsDone(int index) {
        Task task = tasks.get(index);
        task.unmarkAsDone();
        return task.toString();
    }

    /**
     * Prints the list of tasks. If list only has one task (the stub task), it will return list is empty.
     *
     * @return A string representation of the list of tasks.
     */
    public String printList() {
        if (tasks.size() == 1) {
            return "hello! the list is empty :(";
        }

        String list = "";
        for (int i = 1; i < tasks.size(); i++) {
            list += (i + ". " + tasks.get(i) + "\n");
        }
        return list;
    }

    /**
     * Finds tasks that contain the specified keyword.
     *
     * @param keyword The keyword to search for.
     * @return A list of tasks that contain the keyword.
     */
    public ArrayList<Task> find(String keyword) {
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                foundTasks.add(task);
            }
        }
        return foundTasks;
    }
}
