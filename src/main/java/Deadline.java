public class Deadline extends Task {
    String deadline;
    public Deadline(String task, String deadline) {
        super(task);
        this.deadline = deadline;
    }

    @Override
    public String printTask() {
        return "[D]" + super.printTask() + " (by: " + this.deadline + ")";
    }
}
