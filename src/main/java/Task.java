public class Task {
    private String what;
    private String status;
    private String type;

    /**
     * Constructor for Task object.
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
            System.out.println("Nice! I've marked this task as done:");
        } else if (status.equals("unmark")) {
            this.status = "[ ]";
            System.out.println("OK, I've marked this task as not done yet:");
        }
    }

    /**
     * Returns task details in table row form
     *
     * @return String representation of Task to be saved into txt file
     */
    @Override
    public String toString() {
        return (this.status.equals("[X]") ? "t" : "f") + " / " + this.what;
    }
}