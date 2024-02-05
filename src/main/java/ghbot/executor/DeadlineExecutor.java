package ghbot.executor;

import ghbot.Deadline;

public class DeadlineExecutor extends Executor {
    private String description;
    private String deadlineTime;

    public DeadlineExecutor(String description, String deadlineTime) {
        this.description = description;
        this.deadlineTime = deadlineTime;
    }
    @Override
    public void execute() {
        Deadline deadline = new Deadline(this.description, this.deadlineTime);
        this.taskList.addTask(deadline);
        System.out.println("Got it. I've added this task:\n" + deadline);
        System.out.println("Now you have " + this.taskList.taskSize() + " tasks in the list.");
    }
}
