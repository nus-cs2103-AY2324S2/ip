package ghbot.executor;

/**
 * DeleteExecutor Class.
 * Executes "delete" command.
 */
public class DeleteExecutor extends Executor {
    private int lstNo;
    private String executeStr;

    /**
     * DeleteExecutor Constructor.
     * @param lstNo The index of the task that is going to be removed.
     */
    public DeleteExecutor(int lstNo) {
        this.lstNo = lstNo;
        this.executeStr = "";
    }

    /**
     * Prints a string to let user know that the task has been removed.
     */
    @Override
    public String execute() {
        this.executeStr = "Noted. I've removed this task:\n" + this.taskList.getTask(lstNo - 1) + "\n";
        this.taskList.deleteTask(lstNo - 1);
        this.executeStr = this.executeStr + "Now you have " + this.taskList.taskSize() + " tasks in the list.";
        return executeStr;
    }
}
