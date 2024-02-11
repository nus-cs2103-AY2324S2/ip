package duke;

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

    public void addTask(Task task) {
        this.myList.add(task);
    }

    public Task getTask(int i) {
        return this.myList.get(i - 1);
    }

    public void deleteTask(int index) {
        myList.remove(index - 1);
    }

    /**
     * Method to call to get all tasks in a String
     * @return All the tasks contained in the task list object in a String
     */
    public String showTasks() {
        String tasks = "";
        int index = 1;
        for (Task task : this.myList) {
            tasks = tasks + index + ". " + task.toString() + "\n";
        }
        return tasks;
    }

    public int getSize() {
        return this.myList.size();
    }

    public ArrayList<Task> getListOfTasks() {
        return this.myList;
    }

    public void markTask(int index) {
        this.myList.get(index).setDone();
    }

    public void unMarkTask(int index) {
        this.myList.get(index).setNotDone();
    }

    /**
     * Method to find a particular task using matching task name
     * @param text The keyword text to be matched in the task list
     * @return The matching tasks in a string
     */
    public String findTask(String text) {
        String matchingTasks = "";
        int index = 1;
        for (Task task : this.myList) {
            if (task.toString().contains(text)) {
                matchingTasks = index + ". " + task.toString() + "\n";
            }
        }
        return matchingTasks;
    }




}
