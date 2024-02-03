package utilities;

import tasks.Task;

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
    public String add(Task newTask) {
        this.taskArrayList.add(newTask);
        return ResponseHandler.commandPrint(newTask, this.taskArrayList.size());
    }

    /**
     * Prints the entire task list.
     */
    public String printList() {
        return ResponseHandler.commandListPrint(this.taskArrayList);
    }

    /**
     * Changes the status (done or not done) of a task in the list.
     *
     * @param action The action to be performed (mark or unmark).
     * @param which  The index of the task in the list.
     */
    public String changeStatusOfItem(String action, int which) {
        return this.taskArrayList.get(which).changeStatus(action);
    }

    /**
     * Removes a task from the list by its index.
     *
     * @param index The index of the task to be removed.
     */
    public String removeIndex(int index) {
        Task taskToBeRemoved = taskArrayList.get(index);
        this.taskArrayList.remove(index);
        return ResponseHandler.removePrinter(taskToBeRemoved, taskArrayList.size());

    }

    /**
     * Writes the current task list to the hard drive using the provided Storage object.
     *
     * @param storage The Storage object used for writing to the hard drive.
     */
    public void writeToFile(Storage storage) {
        storage.writeToTaskList(this.taskArrayList);
    }


    public String findTasks(String taskPattern) {
        ArrayList<Task> tasksWithPattern = new ArrayList<>();
        for (Task task : this.taskArrayList) {
            String currTaskName = task.getTaskName();
            if (currTaskName.contains(taskPattern)) {
                tasksWithPattern.add(task);
            }
        }
        return ResponseHandler.printFoundTasks(tasksWithPattern);
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
