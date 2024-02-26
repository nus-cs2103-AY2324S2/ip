package dave;

import java.util.ArrayList;

import dave.tasks.Task;
import dave.tasks.Todo;
import dave.tasks.Deadline;
import dave.tasks.Event;

public class TaskList {
    /** The tasks. */
    private ArrayList<Task> taskList;

    /**
     * Creates an empty TaskList object.
     */
    public TaskList() {
        taskList = new ArrayList<Task>();
    }

    /**
     * Initializes a TaskList object, given a list of tasks.
     * 
     * @param taskList Task list to initialize contents from.
     */
    public TaskList(ArrayList<Task> taskListInput) {
        taskList = taskListInput;
    }

    public int getNumberOfTasks() {
        return taskList.size();
    }

    public Task getTask(int index) {
        return taskList.get(index);
    }

    /**
     * Deletes a task from the task list.
     * 
     * @param taskNumber Index of task to delete.
     */
    public void deleteTask(int taskNumber) {
        taskList.remove(taskNumber);
    }

    /**
     * Adds a Todo task to the task list.
     * 
     * @param newTask The new Todo task to be added.
     */
    public void addTask(Todo newTask) {
        taskList.add(newTask);
    }

    /**
     * Adds a Deadline task to the task list.
     * 
     * @param newTask The new Deadline task to be added.
     */
    public void addTask(Deadline newTask) {
        taskList.add(newTask);
    }

    /**
     * Adds a Event task to the task list.
     * 
     * @param newTask The new Event task to be added.
     */
    public void addTask(Event newTask) {
        taskList.add(newTask);
    }
}
