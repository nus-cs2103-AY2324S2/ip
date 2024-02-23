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
     * Returns a string to let user know that the task has been removed.
     * @return A string to let user know that the task has been removed.
     */
    @Override
    public String execute() {
        if (this.lstNo < 1 || this.lstNo > this.taskList.taskSize()) {
            return "Sorry, you may have select a task that is out of scope!\n"
                    + "Please select a number from 1 to " + taskList.taskSize();
        }
        this.executeStr = "Noted. I've removed this task:\n" + this.taskList.getTask(lstNo - 1) + "\n";
        this.taskList.deleteTask(lstNo - 1);
        this.executeStr = this.executeStr + "Now you have " + this.taskList.taskSize() + " tasks in the list.";
        return executeStr;
    }
}
