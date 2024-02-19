package felix;

import java.util.ArrayList;

import task.Task;

/**
 * Encapsulates a taskList. An array full of Tasks object.
 *
 *  @author Tan Qin Yong
 */
public class TaskList {
    /** The list containing tasks. */
    private ArrayList<Task> taskList;

    /** The count of tasks in the list. */
    private int count;

    /**
     * Constructor of taskList.
     *
     * @param taskList A given list of tasks to initialize the felix.TaskList.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
        count = taskList.size();
    }

    /**
     * Constructs an empty felix.TaskList.
     */
    public TaskList() {
        taskList = new ArrayList<Task>();
        count = 0;
    }

    /**
     * Adds a new task to the end of the list.
     *
     * @param newTask The new task provided by the user.
     * @param loadTask Indicates whether task is being loaded by IO.
     */
    public String addTask(Task newTask, boolean loadTask) {
        taskList.add(newTask);
        count++;

        if (!loadTask) {
            String printMessage = "Cool! Adding new task: \n";
            printMessage += newTask.toString();
            printMessage += "\n Now you have " + count + " tasks in your list. \n";

            return printMessage;
        }
        return "";
    }

    /**
     * Deletes a task at the specified index.
     *
     * @param index The specified index given by the user.
     */
    public String deleteTask(int index) {
        assert index > 0 : "Index cannot be less than 1";
        assert index < count : "Index cannot be more than valid tasks";

        Task removedTask = taskList.remove(index - 1);
        count--;

        String printMessage = "Okay. Deleting the task: \n";
        printMessage += removedTask.toString() + "\n";
        printMessage += "Now you have " + count + " tasks in your list \n";
        return printMessage;
    }

    /**
     * Prints tasks containing a given substring.
     *
     * @param keyWord The specified substring given by the user.
     */
    public String findTask(String keyWord) {
        String printMessage = "Here are the matching tasks in your list:  \n";
        int matchCount = 1;
        for (int i = 0; i < count; i++) {
            Task currentTask = taskList.get(i);
            if (currentTask.getDescription().contains(keyWord)) {
                printMessage += matchCount + ". " + currentTask;
                matchCount++;
            }
        }
        return printMessage;
    }

    /**
     * Retrieves a task given the index.
     * The index is 1-based, hence -1 is applied.
     *
     * @param ind The index to retrieve the task.
     * @return The task at the specified index.
     */
    public Task getTask(int ind) {
        return taskList.get(ind - 1);
    }

    /**
     * Sets a task at the specified index.
     * The index is 1-based, hence -1 is applied.
     *
     * @param task The task to set.
     * @param ind  The index to set the task.
     */
    public void setTask(Task task, int ind) {
        taskList.set(ind - 1, task);
    }

    public int getSize() {
        return count;
    }

    /**
     * Marks a task as done at the specified index.
     *
     * @param taskNo The task number to retrieve the task.
     */
    public String markDoneAtInd(int taskNo) {
        assert taskNo > 0 : "Index cannot be less than 1";
        assert taskNo < count : "Index cannot be more than valid tasks";
        Task currentTask = this.getTask(taskNo);
        currentTask.markAsDone();
        this.setTask(currentTask, taskNo);

        return (currentTask.toString());
    }

    /**
     * Marks a task as not done at the specified index.
     *
     * @param taskNo The task number to retrieve the task.
     */
    public String markNotDoneAtInd(int taskNo) {
        assert taskNo > 0 : "Index cannot be less than 1";
        assert taskNo < count : "Index cannot be more than valid tasks";
        Task currentTask = this.getTask(taskNo);
        currentTask.markAsNotDone();
        this.setTask(currentTask, taskNo);

        return (currentTask.toString());
    }

    /**
     * Prints all tasks currently in the taskList.
     */
    public String printAllTasks() {
        String printMessage = "Here are all your tasks so far! ^.^ : \n";
        for (int i = 0; i < count; i++) {
            Task currentTask = taskList.get(i);
            printMessage += ((i + 1) + ". " + currentTask.toString() + "\n");
        }
        return printMessage;
    }
}
