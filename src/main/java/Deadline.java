public class Deadline extends Task {
    protected String deadline;

    public Deadline(String description, boolean status, String deadline) {
        super(TaskType.DEADLINE, description, status);
        this.deadline = deadline;
    }

    public String getDeadline() {
        return this.deadline;
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadline + ")";
    }

}
