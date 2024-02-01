public class Deadline extends Task {
    String deadline;
    public Deadline(String task, String deadline) {
        super(task);
        this.deadline = deadline;
    }

    public Deadline(String task, String deadline, boolean done) {
        super(task, done);
        this.deadline = deadline;
    }

    @Override
    public String printTask() {
        return "[D]" + super.printTask() + " (by: " + this.deadline + ")";
    }

    @Override
    public String toString() {
        int marked = this.done ? 1 : 0;
        return "D | " + marked + " | " + this.task + " | " + this.deadline;
    }
}
