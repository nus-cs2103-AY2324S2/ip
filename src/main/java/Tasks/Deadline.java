package Tasks;

import Tasks.Task;

public class Deadline extends Task {

    String taskType;
    String taskName;
    String deadline;
    Boolean isDone;

    public Deadline(String taskName, String deadline, Boolean isDone, String taskType) {
        super(taskName, isDone, taskType);
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

    @Override
    public String getDeadline() {
        return this.deadline;
    }
}
