package CinnamoRoll;

abstract class Task {
    protected final String taskname;
    private boolean isMarked;

    Task(String str) {
        this.taskname = str;
        this.isMarked = false;
    }

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
     * Retrieve boolean variable isMarked for the child classes
     */
    protected boolean getMarked() {
        return this.isMarked;
    }

    /**
     * Prints the description and timing for the task with the status
     */
    abstract String addTask(int i);

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

    public String toString() {
        return this.getStatusIcon() + " " + this.taskname;
    }
}
