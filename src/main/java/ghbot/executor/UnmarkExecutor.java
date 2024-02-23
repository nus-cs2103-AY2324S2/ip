package ghbot.executor;

/**
 * UnmarkExecutor Class.
 * Executes "unmark" command.
 */
public class UnmarkExecutor extends Executor {
    private int lstNo;
    private String executeStr;

    /**
     * UnmarkExecutor Constructor.
     * @param lstNo The index of the task to unmark.
     */
    public UnmarkExecutor(int lstNo) {
        this.lstNo = lstNo;
        this.executeStr = "";
    }

    /**
     * Returns a string to let user know that the task has been unmarked.
     * @return A string to let user know that the task has been unmarked.
     */
    @Override
    public String execute() {
        for (int i = 0; i < this.taskList.taskSize(); i++) {
            if (i + 1 == lstNo) {
                this.taskList.getTask(i).isNotCompleted();
                this.executeStr = "OK, I've marked this task as not done yet:\n" + this.taskList.getTask(i);
                return this.executeStr;
            }
        }
        return "Sorry, you may have select a task that is out of scope!\n"
                + "Please select a number from 1 to " + taskList.taskSize();
    }
}
