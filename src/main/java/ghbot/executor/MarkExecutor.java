package ghbot.executor;

/**
 * MarkExecutor Class.
 * Executes "mark" command.
 */
public class MarkExecutor extends Executor {
    private int lstNo;

    /**
     * MarkExecutor Constructor.
     * @param lstNo The index of the task to mark.
     */
    public MarkExecutor(int lstNo) {
        this.lstNo = lstNo;
    }

    /**
     * Prints a string to let user know that the selected task is marked.
     */
    @Override
    public void execute() {
        for (int i = 0; i < this.taskList.taskSize(); i++) {
                if (i + 1 == this.lstNo) {
                    this.taskList.getTask(i).isCompleted();
                    System.out.println("Nice! I've marked this task as done:\n" + this.taskList.getTask(i));
                }
            }
    }
}
