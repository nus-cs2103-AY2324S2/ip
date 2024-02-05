public class Deadline extends Task{
    private String deadline;

    public Deadline(String task, String deadline) {
        super(TaskType.DEADLINE, task);
        this.deadline = deadline;
    }

    public Deadline(String task, String deadline, boolean isDone) {
        super(TaskType.DEADLINE, task, isDone);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[" + getTaskType().getIcon() + "]" + "[" + getStatusIcon() + "] " + getTask() + " (by: " + getDeadline() + ")";
    }

    public String getDeadline() {
        return this.deadline;
    }
}
