package ghbot.executor;

/**
 * UnmarkExecutor Class.
 * Executes "unmark" instruction.
 */
public class UnmarkExecutor extends Executor {
    private int listNo;
    private String executeStr;

    /**
     * UnmarkExecutor Constructor.
     * @param listNo The index of the task to unmark.
     */
    public UnmarkExecutor(int listNo) {
        this.listNo = listNo;
        this.executeStr = "";
    }

    /**
     * Returns a string to let user know that the task has been unmarked.
     * @return A string to let user know that the task has been unmarked.
     */
    @Override
    public String execute() {
        for (int i = 0; i < taskList.taskSize(); i++) {
            if (i + 1 == this.listNo) {
                taskList.getTask(i).isNotCompleted();
                this.executeStr = "OK, I've marked this task as not done yet:\n" + taskList.getTask(i);
                return this.executeStr;
            }
        }
        return "Sorry, you may have select a task that is out of scope!\n"
                + "Please select a number from 1 to " + taskList.taskSize();
    }
}
