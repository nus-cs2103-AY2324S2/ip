public class Deadline extends Task{

    private String deadline;

    public Deadline(String deadline, String task) {
        super(task);
        this.deadline = deadline;
    }

    public String getDeadline() {
        return "(by: " + deadline + ")";
    }

    public String isDeadline() {
        return "[D]";
    }

    public String addDeadline() {
        return "Got it. I've added this task:\n"
                + "    " + this.isDeadline() + this.marked() + " "
                + this.getTask()
                + this.getDeadline();
    }
}
