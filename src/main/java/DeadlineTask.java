public class DeadlineTask extends Task {
    private String type;
    private String deadline;

    /**
     * Constructor for Task object of type "deadline".
     *
     * @param what description of the task
     */
    public DeadlineTask(String what, String deadline) {
        super(what);
        this.type = "[D]";
        this.deadline = deadline;
    }

    /**
     * Returns string showing task details.
     *
     * @return string of task type, marked/unmarked status, description and deadline
     */
    public String showAll() {
        return this.type + super.showAll() + "(by: " + this.deadline + ")";
    }
}
