public class DeadlineTask extends Task{
    private String deadline;
    public DeadlineTask(String name, boolean done, String deadline) {
        super(name, done);
        this.deadline = deadline;
    }

    public String getDeadline() {
        return this.deadline;
    }

    @Override
    public String toString() {
        return " [D]" + super.toString() + " (by: " + deadline + ")";
    }
}
