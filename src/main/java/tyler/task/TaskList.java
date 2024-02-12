package tyler.task;

import java.util.ArrayList;

/**
 * Represents the taskList class that hold the taskList in Tyler.
 */
public class TaskList {
    protected ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Get this current taskList.
     *
     * @return A Task ArrayList.
     */
    public ArrayList<Task> getList() {
        return this.taskList;
    }

    /**
     * Add task to the taskList.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Delete task from the taskList.
     *
     * @param index The index of the task to be deleted.
     */
    public void deleteTask(int index) {
        taskList.remove(index);
    }

    /**
     * Get the number of tasks currently in the taskList.
     *
     * @return The number of tasks.
     */
    public int getNumOfTasks() {
        return this.taskList.size();
    }

    /**
     * Get the specific task given index.
     *
     * @param index The index of the task.
     * @return      The task with the specific index.
     */
    public Task getTask(int index) {
        return this.taskList.get(index);
    }

    public void find(String keyword) {
        ArrayList<Task> matchedTasks = new ArrayList<>();
        for (Task task: taskList) {
            if (task.name.toLowerCase().contains(keyword.toLowerCase())) {
                matchedTasks.add(task);
            }
        }
        if (matchedTasks.isEmpty()) {
            System.out.println("    Sorry. No matching tasks found.");
        } else {
            System.out.println("    Here are the matching tasks in your list:");
            for (int i = 0; i < matchedTasks.size(); i++) {
                System.out.println("      " + (i + 1) + ". " + matchedTasks.get(i));
            }
        }
    }
}
