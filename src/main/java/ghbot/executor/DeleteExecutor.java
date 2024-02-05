package ghbot.executor;

/**
 * DeleteExecutor Class.
 * Executes "delete" command.
 */
public class DeleteExecutor extends Executor {
    private int lstNo;

    /**
     * DeleteExecutor Constructor.
     * @param lstNo The index of the task that is going to be removed.
     */
    public DeleteExecutor(int lstNo) {
        this.lstNo = lstNo;
    }

    /**
     * Prints a string to let user know that the task has been removed.
     */
    @Override
    public void execute() {
        System.out.println("Noted. I've removed this task:\n" + this.taskList.getTask(lstNo - 1));
        this.taskList.deleteTask(lstNo - 1);
        System.out.println("Now you have " + this.taskList.taskSize() + " tasks in the list.");
    }
}
