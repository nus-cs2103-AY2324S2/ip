import java.util.*;

/**
 * Encapsulates a taskList. An array full of Tasks object.
 *
 *  @author Tan Qin Yong
 */
public class TaskList {
    /**
     * The list containing tasks.
     */
    private ArrayList<Task> taskList;

    /**
     * The count of tasks in the list.
     */
    private int count;

    /**
     * Constructor of taskList.
     *
     * @param taskList A given list of tasks to initialize the TaskList.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
        this.count = taskList.size();
    }

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.taskList = new ArrayList<Task>();
        this.count = 0;
    }

    /**
     * Adds a new task to the end of the list.
     *
     * @param newTask The new task provided by the user.
     */
    public void addTask(Task newTask) {
        this.taskList.add(newTask);
        count++;

        System.out.println("Cool! Adding new task: ");
        System.out.println(newTask.toString());
        System.out.println("Now you have " + this.count +
                            " tasks in your list.");
    }

    /**
     * Deletes a task at the specified index.
     *
     * @param index The specified index given by the user.
     */
    public void deleteTask(int index) {
        Task removedTask = this.taskList.remove(index-1);
        count--;

        System.out.println("Okay. Deleting the task: ");
        System.out.println(removedTask.toString());
        System.out.println("Now you have " + this.count +
                " tasks in your list.");
    }

    /**
     * Retrieves a task given the index.
     * The index is 1-based, hence -1 is applied.
     *
     * @param ind The index to retrieve the task.
     * @return The task at the specified index.
     */
    public Task getTask(int ind) {
        return this.taskList.get(ind-1);
    }

    /**
     * Sets a task at the specified index.
     * The index is 1-based, hence -1 is applied.
     *
     * @param task The task to set.
     * @param ind  The index to set the task.
     */
    public void setTask(Task task, int ind) {
        this.taskList.set(ind-1, task);
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
            Task currentTask = this.taskList.get(i);
            System.out.println((i + 1) + ". " + currentTask.toString());
        }
    }
}
