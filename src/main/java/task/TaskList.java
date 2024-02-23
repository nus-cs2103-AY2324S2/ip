package task;

import java.util.ArrayList;

import comparator.TaskComparator;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    private final ArrayList<Task> listOfTasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.listOfTasks = new ArrayList<>();
    }

    /**
     * Sorts the list of tasks based on their names in alphabetical order.
     */
    public void sort() {
        listOfTasks.sort(new TaskComparator<>());
    }

    /**
     * Constructs a TaskList with the given list of tasks.
     *
     * @param taskList The list of tasks to initialize the TaskList.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.listOfTasks = taskList;
    }

    /**
     * Adds a task to the task list.
     * 
     * @param newTask The task to be added.
     */
    public void addTask(Task newTask) {
        listOfTasks.add(newTask);
    }

    /**
     * Retrieves the task at the specified index in the task list.
     *
     * @param index The index of the task to retrieve.
     * @return The task at the specified index.
     */
    public Task getTask(int index) {
        assert index >= 0 && index < listOfTasks.size() : "Index out of bounds";
        return listOfTasks.get(index);
    }

    /**
     * Returns the number of tasks in the task list.
     * 
     * @return The number of tasks in the task list.
     */
    public int getSize() {
        return listOfTasks.size();
    }

    /**
     * Removes the task at the specified index from the task list.
     * 
     * @param index The index of the task to remove.
     */
    public void removeTask(int index) {
        assert index >= 0 && index < listOfTasks.size() : "Index out of bounds";
        listOfTasks.remove(index);
    }

    /**
     * Returns the list of tasks.
     * 
     * @return The list of tasks.
     */
    public ArrayList<Task> getList() {
        return listOfTasks;
    }
}
