package tasks;

/**
 * Represents a task with task description, completion status and task type as available details.
 */
public class Task {
    private String what;
    private String status;
    private String type;

    /**
     * Constructor for tasks.Task object.
     *
     * @param what description of the task
     */
    public Task(String what, String status, String type) {
        this.what = what;
        this.type = type;
        if (status.equals("t")) {
            this.status = "[X]";
        } else {
            this.status = "[ ]";
        }
    }

    /**
     * Returns string showing task details.
     *
     * @return string of marked/unmarked status and task description
     */
    public String showAll() {
        return this.type + this.status + " " + this.what;
    }

    /**
     * Marks tasks as done or undone.
     *
     * @param status completion status of task
     */
    public void changeStatus(String status) {
        if (status.equals("mark")) {
            this.status = "[X]";
        } else if (status.equals("unmark")) {
            this.status = "[ ]";
        }
    }

    /**
     * Returns boolean result of whether task description contains a given substring
     *
     * @param str String to be compared with task description
     * @return true if String is substring of task description, false otherwise
     */
    public boolean isPartOfDesc(String str) {
        if (this.what.contains(str)) {
            return true;
        }
        return false;
    }

    /**
     * Returns task details in table row form
     *
     * @return String representation of tasks.Task to be saved into txt file
     */
    @Override
    public String toString() {
        return (this.status.equals("[X]") ? "t" : "f") + " / " + this.what;
    }
}