package action.task;

public class DeadlineTask extends Task{
    private String by;
    public DeadlineTask(String taskName, String by) {
        super(taskName);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
