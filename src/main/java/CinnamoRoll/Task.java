package CinnamoRoll;

abstract class Task {
    protected final String taskname;
    boolean is_marked;

    Task(String str) {
        this.taskname = str;
        this.is_marked = false;
    }

    Task(String str, boolean marked) {
        this.taskname = str;
        this.is_marked = marked;
    }
    /**
     * Updates the marked status of the tasks stored in the list to be true (marked)
     */
    protected void marked() {
        this.is_marked = true;
    }
    /**
     * Updates the marked status of the tasks stored in the list to be false (unmarked)
     */
    protected void unmarked() {
        this.is_marked = false;
    }
    /**
     * Retrieve status icon in the form of [(task type)][(marked status)]
     */
    abstract String getStatusIcon();
    /**
     * Prints the description and timing for the task with the status
     */
    abstract String added(int i);

    public String toString() {
        return this.getStatusIcon() + " " + this.taskname;
    }
}