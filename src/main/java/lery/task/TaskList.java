package lery.task;

import java.util.ArrayList;

/**
 * Represents a collection of tasks in the Lery chatbot application.
 * It manages tasks, such as adding, deleting, and retrieving tasks from the list,
 * as well as providing a method to print the list of tasks.
 *
 * The class contains methods to manipulate the task list, including adding, deleting,
 * getting the size, retrieving a specific task by index, and printing the list.
 */
public class TaskList {
    private ArrayList<Task> taskList;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    /**
     * Constructs a TaskList with an existing list of tasks.
     *
     * @param tl the ArrayList of tasks to initialize the TaskList.
     */
    public TaskList(ArrayList<Task> tl) {
        this.taskList = tl;
    }

    /**
     * Adds a task to the task list.
     *
     * @param t the task to be added.
     */
    public void add(Task t) {
        this.taskList.add(t);
    }

    /**
     * Deletes a task from the task list.
     *
     * @param t the task to be deleted.
     */
    public void delete(Task t) {
        this.taskList.remove(t);
    }

    /**
     * Gets the size of the task list.
     *
     * @return the number of tasks in the list.
     */
    public int getSize() {
        return this.taskList.size();
    }

    /**
     * Retrieves a task from the task list by index.
     *
     * @param id the index of the task to be retrieved.
     * @return the task at the specified index.
     */
    public Task getTask(int id) {
        return this.taskList.get(id);
    }

    /**
     * Prints the list of tasks in a formatted string.
     *
     * @return a string containing the formatted list of tasks.
     */
    public String printList() {
        String list = "Here are the tasks in your list:\n";
        for (int i = 0; i < this.getSize(); i++) {
            Task task = this.getTask(i);
            String message = i + 1 + "." + "[" + task.getType()
                    + "]" + "[" + task.getStatusIcon() + "] " + task.getDescription()
                    + task.getExtraInfo() + "\n";
            list += message;
        }
        return list;
    }


}
