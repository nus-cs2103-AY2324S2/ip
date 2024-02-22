package homie;

import java.util.ArrayList;

/**
 * TaskList class that handles everything related to storing tasks.
 * Contains methods to add tasks, delete tasks, get task, mark tasks as done/undone,
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
     * Adds new task into the task list.
     *
     * @param task The task to be added to the task list.
     */
    public void addTask(Task task) {
        this.myList.add(task);
    }

    /**
     * Returns the task with the specified index in the task list.
     *
     * @param taskIndex Index of the task in the task list.
     * @return The task with the specified index.
     */
    public Task getTask(int taskIndex) {
        return this.myList.get(taskIndex - 1);
    }

    /**
     * Deletes the task with the specified index from the task list.
     *
     * @param taskIndex The index of the task to be deleted.
     */
    public void deleteTask(int taskIndex) {
        myList.remove(taskIndex - 1);
    }

    /**
     * Gets all tasks from the task list.
     *
     * @return All the tasks contained in the task list object in a String.
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
     * Returns the size of the task list.
     *
     * @return The size of the task list.
     */
    public int getSize() {
        return this.myList.size();
    }

    /**
     * Returns an Array list of the task list.
     *
     * @return The Array list of the task list.
     */
    public ArrayList<Task> getListOfTasks() {
        return this.myList;
    }

    /**
     * Marks a task in the task list as done.
     *
     * @param taskIndex Index of the task to be marked in the task list.
     */
    public void markTask(int taskIndex) {
        this.myList.get(taskIndex - 1).setDone();
    }

    /**
     * Unmarks a task in the task list as done.
     *
     * @param taskIndex Index of the task to be marked in the task list.
     */
    public void unMarkTask(int taskIndex) {
        this.myList.get(taskIndex - 1).setNotDone();
    }

    /**
     * Finds all tasks with matching keyword.
     *
     * @param text The keyword to be matched in the task list
     * @return The matching tasks in a string
     */
    public String findTask(String text) {
        String matchingTasks = "";
        int index = 1;
        for (Task task : this.myList) {
            if (task.toString().contains(text)) {
                matchingTasks = matchingTasks + "\t" + index + ". " + task + "\n";
                index++;
            }
        }
        return matchingTasks;
    }




}
