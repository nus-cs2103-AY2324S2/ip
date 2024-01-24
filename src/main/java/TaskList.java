/** Encapsulates a taskList. An array full of Tasks object.
 * @author Tan Qin Yong
 */
public class TaskList {
    private Task[] taskList;
    private int count;

    /**
     * Constructor of taskList.
     *
     * @param taskList a given list of task.
     */
    public TaskList(Task[] taskList) {
        this.taskList = taskList;
        this.count = taskList.length;
    }

    /**
     * Overloaded empty constructor.
     */
    public TaskList() {
        this.taskList = new Task[100];
        this.count = 0;
    }

    /**
     * Adds a new task to the end of the list.
     *
     * @param newTask a new task given by user.
     */
    public void addTask(Task newTask) {
        this.taskList[count] = newTask;
        count++;
    }

    /**
     * Get a task given the index.
     * The index given will be 1-based, hence -1.
     *
     * @param ind the index to retrieve the task.
     */
    public Task getTask(int ind) {
        return this.taskList[ind-1];
    }

    /**
     * Set a task given the index.
     * The index given will be 1-based, hence -1.
     *
     * @param ind the index to retrieve the task.
     */
    public void setTask(Task task, int ind) {
        this.taskList[ind-1] = task;
    }

    /**
     * Mark task as done at given index.
     * Get task already minuses 1. So pass in the taskNo.
     *
     * @param ind the index to retrieve the task.
     */
    public void markDoneAtInd(int taskNo) {
        Task currentTask = this.getTask(taskNo);
        currentTask.markAsDone();
        this.setTask(currentTask, taskNo);

        System.out.println(
                "  " + currentTask.getStatusIcon() +
                        " " + currentTask.getDescription()
        );
    }

    /**
     * Mark task as not done at given index.
     * Get task already minuses 1. So pass in the taskNo.
     *
     * @param ind the index to retrieve the task.
     */
    public void markNotDoneAtInd(int taskNo) {
        Task currentTask = this.getTask(taskNo);
        currentTask.markAsNotDone();
        this.setTask(currentTask, taskNo);

        System.out.println(
                "  " + currentTask.getStatusIcon() +
                        " " + currentTask.getDescription()
        );
    }

    /**
     * Prints all tasks current in the taskList.
     */
    public void printAllTasks() {
        System.out.println("Here are all your tasks so far! ^.^ : ");
        for (int i = 0; i < count; i++) {
            Task currentTask = this.taskList[i];
            System.out.println(
                    (i + 1) + ". " +
                    currentTask.getStatusIcon() + " " +
                    currentTask.getDescription()
            );
        }
    }
}
