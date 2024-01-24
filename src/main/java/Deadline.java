public class Deadline extends Task {
    String deadline;

    public Deadline(String str, String deadline) {
        super(str);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D][" + getStatusIcon() + "] " + this.desc + " (by: " + this.deadline + ")";
    }
}
