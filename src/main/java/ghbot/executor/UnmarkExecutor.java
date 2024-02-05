package ghbot.executor;

/**
 * UnmarkExecutor Class.
 * Executes "unmark" command.
 */
public class UnmarkExecutor extends Executor {
    private int lstNo;

    /**
     * UnmarkExecutor Constructor.
     * @param lstNo The index of the task to unmark.
     */
    public UnmarkExecutor(int lstNo) {
        this.lstNo = lstNo;
    }

    /**
     * Prints a string to let user know that the task has been unmarked.
     */
    @Override
    public void execute() {
        for (int i = 0; i < this.taskList.taskSize(); i++) {
            if (i + 1 == lstNo) {
                this.taskList.getTask(i).isNotCompleted();
                System.out.println("OK, I've marked this task as not done yet:\n" + this.taskList.getTask(i));
            }
        }
    }
}
