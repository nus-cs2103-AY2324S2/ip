package dave;

import java.util.ArrayList;

import dave.tasks.*;

public class TaskList {
    /** The tasks. */
    private ArrayList<Task> taskList;

    /**
     * Creates an empty TaskList object.
     */
    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    /**
     * Initializes a TaskList object, given a list of tasks.
     * 
     * @param taskList Task list to initialize contents from.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public int getNumberOfTasks() {
        return this.taskList.size();
    }

    public Task getTask(int index) {
        return this.taskList.get(index);
    }

    /**
     * Deletes a task from the task list.
     * 
     * @param taskNumber Index of task to delete.
     */
    public void deleteTask(int taskNumber) {
        this.taskList.remove(taskNumber);
    }

    /**
     * Adds a Todo task to the task list.
     * 
     * @param newTask The new Todo task to be added.
     */
    public void addTask(Todo newTask) {
        this.taskList.add(newTask);
    }

    /**
     * Adds a Deadline task to the task list.
     * 
     * @param newTask The new Deadline task to be added.
     */
    public void addTask(Deadline newTask) {
        this.taskList.add(newTask);
    }

    /**
     * Adds a Event task to the task list.
     * 
     * @param newTask The new Event task to be added.
     */
    public void addTask(Event newTask) {
        this.taskList.add(newTask);
    }
}
