package ghbot.executor;

import ghbot.task.Deadline;

/**
 * DeadlineExecutor Class.
 * Executes "Deadline" instruction.
 */
public class DeadlineExecutor extends Executor {
    private String description;
    private String deadlineTime;
    private String executeStr;

    /**
     * DeadlineExecutor Constructor.
     * @param description A description of the deadline.
     * @param deadlineTime The time of the deadline.
     */
    public DeadlineExecutor(String description, String deadlineTime) {
        this.description = description;
        this.deadlineTime = deadlineTime;
        this.executeStr = "";
    }

    /**
     * Returns a string to let user know that they have successfully added a deadline task.
     * @return A string to let user know that they have successfully added a deadline task.
     */
    @Override
    public String execute() {
        Deadline deadline = new Deadline(this.description, this.deadlineTime);
        taskList.addTask(deadline);
        this.executeStr = "Got it. I've added this task:\n" + deadline + "\n";
        this.executeStr = this.executeStr + "Now you have " + taskList.taskSize() + " tasks in the list.";
        return this.executeStr;
    }
}
