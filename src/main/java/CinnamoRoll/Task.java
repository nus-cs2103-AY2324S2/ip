package CinnamoRoll;

/**
 * Abstracts out the main features of tasks and
 * specifies / sets functions that task subclasses can perform
 */
abstract class Task {
    protected final String taskname;
    protected boolean isMarked;

    /**
     * Creates a new Task with the given description.
     *
     * @param str The description of the task.
     */
    Task(String str) {
        this.taskname = str;
        this.isMarked = false;
    }

    /**
     * Creates a new Task with the given description and marked status.
     *
     * @param str     The description of the task.
     * @param marked  A boolean indicating whether the task is marked as completed.
     */
    Task(String str, boolean marked) {
        this.taskname = str;
        this.isMarked = marked;
    }
    /**
     * Updates the marked status of the tasks stored in the list to be true (marked)
     */
    protected void markTask() {
        this.isMarked = true;
    }

    /**
     * Updates the marked status of the tasks stored in the list to be false (unmarked)
     */
    protected void unmarkTask() {
        this.isMarked = false;
    }

    /**
     * Retrieve status icon in the form of [(task type)][(marked status)]
     */
    abstract String getStatusIcon();

    /**
     * Retrieve the marking status of the event in boolean attribute
     */
    protected boolean getMarked() {
        return this.isMarked;
    }

    /**
     * Prints the description and timing for the task with the status
     */
    abstract String addTask(int i);

    /**
     * Returns a string of the task so that it can be stored into the
     * database with a correct format to load data in future
     */
    abstract String storeInFile();

    /**
     * Checks whether task's taskname contains the given string for find method
     */
    protected boolean containString(String str) {
        if (this.taskname.contains(str)) {
            return true;
        } else {
            return false;
        }
    }

    protected String getTaskName() {
        return this.taskname;
    }

    /**
     * Outputs a string output of a task that contains taskname, task type,
     * and relevant time information.
     */
    public String toString() {
        return this.getStatusIcon() + " " + this.taskname;
    }
}
