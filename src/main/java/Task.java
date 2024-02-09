public class Task {
    private String what;
    private String done;

    /**
     * Constructor for Task object.
     *
     * @param what description of the task
     */
    public Task(String what) {
        this.what = what;
        this.done = "[ ]";
    }

    /**
     * Returns string showing of task completion status and description.
     *
     * @return string of marked/unmarked status and task description
     */
    public String showAll() {
        return this.done + " " + this.what;
    }

    /**
     * Marks tasks as done.
     */
    public void mark() {
        this.done = "[X]";
        System.out.println("Nice! I've marked this task as done:");
    }

    /**
     * Marks tasks as undone.
     */
    public void unmark() {
        this.done = "[ ]";
        System.out.println("OK, I've marked this task as not done yet:");
    }
}