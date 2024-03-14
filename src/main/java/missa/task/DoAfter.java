package missa.task;

/**
 * DoAfter class represents tasks that have a preceding condition.
 */
public class DoAfter extends Task {
    private String taskCondition;

    /**
     * Creates a do-after task for user.
     *
     * @param content Content of the task.
     * @param taskCondition Conditions that need to be satisfied before starting this task.
     */
    public DoAfter(String content, String taskCondition) {
        super(content);
        this.taskCondition = taskCondition;
    }

    @Override
    public String toString() {
        return "[DA]"
                + super.toString()
                + " (after "
                + this.taskCondition
                + ")";
    }

    @Override
    public String getData() {
        return "DA" + super.getData() + " | " + this.taskCondition;
    }
}
