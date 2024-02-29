package homie;

import java.util.ArrayList;

/**
 * TaskList class that handles everything related to storing tasks.
 * Provides methods to add tasks, delete tasks, get task, mark tasks as done/undone,
 * show tasks, find tasks, get task list size, and get the whole list of tasks.
 */
public class TaskList {
    private ArrayList<Task> myList;

    public TaskList(ArrayList<Task> myList) {
        this.myList = myList;
    }

    public TaskList() {
        this.myList = new ArrayList<>();
    }

    /**
     * Adds a new task into the TaskList.
     *
     * @param task The task to be added to the TaskList object.
     */
    public void addTask(Task task) {
        this.myList.add(task);
    }

    /**
     * Returns the task with the specified index in the TaskList object.
     *
     * @param taskIndex Index of the task in the TaskList.
     * @return The task with the specified index.
     */
    public Task getTask(int taskIndex) {
        return this.myList.get(taskIndex - 1);
    }

    /**
     * Deletes the task with the specified index from the TaskList object.
     *
     * @param taskIndex The index of the task to be deleted.
     */
    public void deleteTask(int taskIndex) {
        myList.remove(taskIndex - 1);
    }

    /**
     * Gets all tasks from the TaskList.
     *
     * @return All the tasks contained in the TaskList object as a String.
     */
    public String getTasks() {
        String tasks = "";
        int index = 1;
        for (Task task : this.myList) {
            tasks = tasks + "\t" + index + ". " + task.toString() + "\n";
            index++;
        }
        return tasks;
    }

    /**
     * Returns the size of the TaskList object.
     *
     * @return The integer representation of the size of the TaskList object.
     */
    public int getSize() {
        return this.myList.size();
    }

    /**
     * Returns an Array list of the TaskList object.
     *
     * @return The Array list of the TaskList object.
     */
    public ArrayList<Task> getListOfTasks() {
        return this.myList;
    }

    /**
     * Marks a task in the TaskList object as done.
     *
     * @param taskIndex Index of the task to be marked in the TaskList object.
     */
    public void markTask(int taskIndex) {
        this.myList.get(taskIndex - 1).setDone();
    }

    /**
     * Marks a task in the TaskList as not done.
     *
     * @param taskIndex Index of the task to be marked in the TaskList.
     */
    public void unMarkTask(int taskIndex) {
        this.myList.get(taskIndex - 1).setNotDone();
    }

    /**
     * Finds all tasks with matching keyword from the TaskList object.
     *
     * @param keyword The keyword to find for matching tasks in the TaskList object.
     * @return All the matching tasks from the TaskList object as a String.
     */
    public String findTask(String keyword) {
        String matchingTasks = "";
        int index = 1;
        for (Task task : this.myList) {
            if (task.toString().contains(keyword)) {
                matchingTasks = matchingTasks + "\t" + index + ". " + task + "\n";
                index++;
            }
        }
        return matchingTasks;
    }
}
