public class DeadlineTask extends Task {
    private String type;
    private String deadline;

    /**
     * Constructor for Task object of type "deadline".
     *
     * @param what description of the task
     */
    private DeadlineTask(String what, String deadline) {
        super(what);
        this.type = "[D]";
        this.deadline = deadline;
    }

    /**
     * Factory method for DeadlineTask object
     *
     * @param arr String array with task details
     * @return DeadlineTask object with task details in fields
     */
    public static DeadlineTask of(String[] arr) {
        String[] hasWhat = arr[1].split("/", 2);
        String[] hasTime = hasWhat[1].split(" ", 2);
        return new DeadlineTask(hasWhat[0], hasTime[1]);
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
