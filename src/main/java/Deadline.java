public class Deadline extends Task {

    String taskType;
    String taskName;
    String deadline;
    Boolean isDone;

    public Deadline(String taskName, String deadline) {
        super(taskName);
        this.taskType = "D";
        this.deadline = deadline;
    }

    public void mark() {
        super.mark();
    }

    public void unmark() {
        super.unmark();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadline + ")";
    }
}
