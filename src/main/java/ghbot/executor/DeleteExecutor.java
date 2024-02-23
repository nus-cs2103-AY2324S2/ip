package ghbot.executor;

/**
 * DeleteExecutor Class.
 * Executes "delete" instruction.
 */
public class DeleteExecutor extends Executor {
    private int listNo;
    private String executeStr;

    /**
     * DeleteExecutor Constructor.
     * @param listNo The index of the task that is going to be removed.
     */
    public DeleteExecutor(int listNo) {
        this.listNo = listNo;
        this.executeStr = "";
    }

    /**
     * Returns a string to let user know that the task has been removed.
     * @return A string to let user know that the task has been removed.
     */
    @Override
    public String execute() {
        if (this.listNo < 1 || this.listNo > taskList.taskSize()) {
            return "Sorry, you may have select a task that is out of scope!\n"
                    + "Please select a number from 1 to " + taskList.taskSize();
        }
        this.executeStr = "Noted. I've removed this task:\n" + taskList.getTask(this.listNo - 1) + "\n";
        taskList.deleteTask(this.listNo - 1);
        this.executeStr = this.executeStr + "Now you have " + taskList.taskSize() + " tasks in the list.";
        return this.executeStr;
    }
}
