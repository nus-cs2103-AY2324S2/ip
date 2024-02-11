public class Task {
    private String what;
    private String status;

    /**
     * Constructor for Task object.
     *
     * @param what description of the task
     */
    public Task(String what) {
        this.what = what;
        this.status = "[ ]";
    }

    /**
     * Returns string showing task details.
     *
     * @return string of marked/unmarked status and task description
     */
    public String showAll() {
        return this.status + " " + this.what;
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
}