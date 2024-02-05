package duke;

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
     * @param taskList A given list of tasks to initialize the duke.TaskList.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
        count = taskList.size();
    }

    /**
     * Constructs an empty duke.TaskList.
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
    public void addTask(Task newTask, boolean loadTask) {
        taskList.add(newTask);
        count++;

        if (!loadTask) {
            System.out.println("Cool! Adding new task: ");
            System.out.println(newTask.toString());
            System.out.println("Now you have " + this.count
                    + " tasks in your list.");
        }
    }

    /**
     * Deletes a task at the specified index.
     *
     * @param index The specified index given by the user.
     */
    public void deleteTask(int index) {
        Task removedTask = taskList.remove(index - 1);
        count--;

        System.out.println("Okay. Deleting the task: ");
        System.out.println(removedTask.toString());
        System.out.println("Now you have " + count
                + " tasks in your list.");
    }

    /**
     * Prints tasks containing a given substring.
     *
     * @param keyWord The specified substring given by the user.
     */
    public void findTask(String keyWord) {
        System.out.println("Here are the matching tasks in your list: ");
        int matchCount = 1;
        for (int i = 0; i < count; i++) {
            Task currentTask = taskList.get(i);
            if (currentTask.getDescription().contains(keyWord)) {
                System.out.println(matchCount + ". " + currentTask);
                matchCount++;
            }
        }
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
    public void markDoneAtInd(int taskNo) {
        Task currentTask = this.getTask(taskNo);
        currentTask.markAsDone();
        this.setTask(currentTask, taskNo);

        System.out.println(currentTask.toString());
    }

    /**
     * Marks a task as not done at the specified index.
     *
     * @param taskNo The task number to retrieve the task.
     */
    public void markNotDoneAtInd(int taskNo) {
        Task currentTask = this.getTask(taskNo);
        currentTask.markAsNotDone();
        this.setTask(currentTask, taskNo);

        System.out.println(currentTask.toString());
    }

    /**
     * Prints all tasks currently in the taskList.
     */
    public void printAllTasks() {
        System.out.println("Here are all your tasks so far! ^.^ : ");
        for (int i = 0; i < count; i++) {
            Task currentTask = taskList.get(i);
            System.out.println((i + 1) + ". " + currentTask.toString());
        }
    }
}
