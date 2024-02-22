package gronk;

/**
 * Generic Task class.
 * Todo, Deadline, and Event subclasses all
 * inherit from this Task class.
 */
public class Task {
    private String desc;
    private int status; // 0 incomplete, 1 complete

    /**
     * Constructor for Task object.
     * @param d String containing description of task.
     * @param s Integer containing status of task.
     */
    public Task(String d, int s) {
        this.desc = d;
        this.status = s;
    }

    /**
     * Setter method for status of Task.
     *
     * @param i New integer status of class.
     */
    public void update(int i) {
        this.status = i;
    }

    /**
     * Setter method for status of Task.
     * Flips the current status of Task to its opposite.
     */
    public void flip() {
        if (this.status == 0) {
            this.status = 1;
        } else {
            this.status = 0;
        }
    }

    /**
     * Getter for status of Task.
     *
     * @return Integer representing status of Task.
     */
    public int getStatus() {
        return this.status;
    }

    /**
     * Getter for description of Task.
     *
     * @return String containing description of Task.
     */
    public String getDesc() {
        return this.desc;
    }

    /**
     * Getter method for the updated status of a Task.
     * This method will be overridden by the subclasses.
     *
     * @return Empty string
     */
    public String statusMessage() {
        return "";
    }

    /**
     * Converts a Task object into a String for writing to a file.
     *
     * @return String representing saved format of Task object.
     */
    public String saveFormat() {
        return "";
    }

    /**
     * Overridden toString method.
     *
     * @return String format of Task object.
     */
    @Override
    public String toString() {
        if (this.status == 0) {
            return "[ ] " + this.desc;
        } else {
            return "[X] " + this.desc;
        }
    }
}
