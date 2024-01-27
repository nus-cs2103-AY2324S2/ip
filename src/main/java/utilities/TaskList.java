package utilities;

import tasks.Task;
import utilities.Storage;

import java.util.ArrayList;

/**
 * The TaskList class represents a list of tasks and provides methods for manipulating and interacting with the list.
 */
public class TaskList {
    /**
     * The ArrayList containing the tasks in the task list.
     */
    private ArrayList<Task> taskArrayList;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.taskArrayList = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with the specified list of tasks.
     *
     * @param taskList The initial list of tasks to be added to the TaskList.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskArrayList = taskList;
    }

    /**
     * Adds a new task to the task list.
     *
     * @param newTask The task to be added to the list.
     */
    public void add(Task newTask) {
        this.taskArrayList.add(newTask);
        MessagePrinter.commandPrint(newTask, this.taskArrayList.size());
    }

    /**
     * Prints the entire task list.
     */
    public void printList() {
        MessagePrinter.commandListPrint(this.taskArrayList);
    }

    /**
     * Changes the status (done or not done) of a task in the list.
     *
     * @param action The action to be performed (mark or unmark).
     * @param which  The index of the task in the list.
     */
    public void changeStatusOfItem(String action, int which) {
        this.taskArrayList.get(which).changeStatus(action);
    }

    /**
     * Removes a task from the list by its index.
     *
     * @param index The index of the task to be removed.
     */
    public void removeIndex(int index) {
        MessagePrinter.removePrinter(this.taskArrayList.get(index), this.taskArrayList.size());
        this.taskArrayList.remove(index);
    }

    /**
     * Writes the current task list to the hard drive using the provided Storage object.
     *
     * @param storage The Storage object used for writing to the hard drive.
     */
    public void writeToFile(Storage storage) {
        storage.writeToTaskList(this.taskArrayList);
    }


    public void findTasks(String taskPattern) {
        ArrayList<Task> tasksWithPattern = new ArrayList<>();
        for (int i = 0; i < this.taskArrayList.size(); i += 1) {
            String currTaskName = taskArrayList.get(i).getTaskName();
            if (currTaskName.contains(taskPattern)) {
                tasksWithPattern.add(taskArrayList.get(i));
            }
        }
        MessagePrinter.printFoundTasks(tasksWithPattern);
    }


    /**
     * Overrides the toString method to provide information about the number of tasks in the list.
     *
     * @return A string representation of the number of tasks in the list.
     */
    @Override
    public String toString() {
        return "Now you have " + taskArrayList.size() + " tasks in the list.";
    }
}