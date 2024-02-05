public class Deadline extends Task{
    private String deadline;

    public Deadline(String task, String deadline) {
        super(TaskType.DEADLINE, task);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[" + getIcon() + "]" + "[" + getStatusIcon() + "] " + getTask() + " (by: " + getDeadline() + ")";
    }

    public String getDeadline() {
        return this.deadline;
    }
}
