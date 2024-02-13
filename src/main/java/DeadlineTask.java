import java.time.LocalDateTime;

public class DeadlineTask extends Task {
    private LocalDateTime deadline;

    /**
     * Constructor for Task object of type "deadline".
     *
     * @param what description of the task
     * @param status completion status of task
     * @param deadline deadline of task
     */
    public DeadlineTask(String what, String status, String deadline) {
        super(what, status, "[D]");
        String[] arr = deadline.split(" ");
        this.deadline = LocalDateTime.parse(arr[0] + "T" + arr[1] + ":00");
    }

    /**
     * Factory method for DeadlineTask object
     *
     * @param str String array with task details
     * @return DeadlineTask object with task details in fields
     */
    public static DeadlineTask of(String str) {
        String[] hasWhat = str.split("/", 2);
        String[] hasWhen = hasWhat[1].split(" ", 2);
        return new DeadlineTask(hasWhat[0], "f", hasWhen[1]);
    }

    /**
     * Returns string showing task details.
     *
     * @return string of task type, marked/unmarked status, description and deadline
     */
    public String showAll() {
        String[] arr = this.deadline.toString().split("T");
        return super.showAll() + "(by: " + arr[0] + " " + arr[1] + ")";
    }

    /**
     * Returns DeadlineTask details in table row form
     *
     * @return String representation of DeadlineTask to be saved into txt file
     */
    @Override
    public String toString() {
        String[] arr = this.deadline.toString().split("T");
        return "D / " + super.toString() + " / " + arr[0] + " " + arr[1];
    }
}
