package Task;

import Task.Task;

public class DeadlineTask extends Task {

    private  String deadline;

    public DeadlineTask(String taskName, String deadline) {
        super(taskName);
        this.deadline = deadline;
    }

    public DeadlineTask(String taskName, String deadline, boolean isCompleted) {
        super(taskName, isCompleted);
        this.deadline = deadline;
    }

    @Override
    public String getStringStorageRepresentation() {
        return String.format("D | %s | %s", super.getStringStorageRepresentation(), this.deadline);
    }

    @Override
    public String toString() {
        return String.format("[D] %s (by: %s)", super.toString(), this.deadline);
    }
}
