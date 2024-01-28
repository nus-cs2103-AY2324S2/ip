public class DeadlineTask extends Task{
    private String deadline;
    public DeadlineTask(String name, boolean done, String deadline) {
        super(name, done);
        this.deadline = deadline;
        //put date formatter here
        DateFormatter d = new DateFormatter(this.deadline);
        if (d.isValidDate()) {
            this.deadline = d.convertDate();
        }
    }

    public String getDeadline() {
        return this.deadline;
    }

    public String getType() {
        return "D";
    }
    @Override
    public String toString() {
        return " [D]" + super.toString() + " (by: " + deadline + ")";
    }
}
