package ghbot.executor;

import ghbot.Deadline;

/**
 * DeadlineExecutor Class.
 * Executes "Deadline" command.
 */
public class DeadlineExecutor extends Executor {
    private String description;
    private String deadlineTime;

    /**
     * DeadlineExecutor Constructor.
     * @param description A description of the deadline.
     * @param deadlineTime The time of the dateline.
     */
    public DeadlineExecutor(String description, String deadlineTime) {
        this.description = description;
        this.deadlineTime = deadlineTime;
    }

    /**
     * Prints a string to let user know that they have successfully added a deadline task.
     */
    @Override
    public void execute() {
        Deadline deadline = new Deadline(this.description, this.deadlineTime);
        this.taskList.addTask(deadline);
        System.out.println("Got it. I've added this task:\n" + deadline);
        System.out.println("Now you have " + this.taskList.taskSize() + " tasks in the list.");
    }
}
