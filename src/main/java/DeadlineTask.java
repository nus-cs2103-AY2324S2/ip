public class DeadlineTask extends Task {
    private String type;
    private String deadline;

    /**
     * Constructor for Task object of type "deadline".
     *
     * @param what description of the task
     * @param status completion status of task
     * @param deadline deadline of task
     */
    public DeadlineTask(String what, String status, String deadline) {
        super(what, status);
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
        return new DeadlineTask(hasWhat[0], "f", hasTime[1]);
    }

    /**
     * Returns string showing task details.
     *
     * @return string of task type, marked/unmarked status, description and deadline
     */
    public String showAll() {
        return this.type + super.showAll() + "(by: " + this.deadline + ")";
    }

    /**
     * Returns DeadlineTask details in table row form
     *
     * @return String representation of DeadlineTask to be saved into txt file
     */
    @Override
    public String toString() {
        return "D / " + super.toString() + " / " + this.deadline;
    }
}
